package com.example.converterrate.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converterrate.domain.entity.AccountDomain
import com.example.currencyconverter.domain.useCase.DeleteTransactionUseCase
import com.example.currencyconverter.domain.useCase.GetAccountUseCase
import com.example.currencyconverter.domain.useCase.GetRateUseCase
import com.example.currencyconverter.domain.useCase.GetTransactionUseCase
import com.example.currencyconverter.domain.useCase.MoveSelectedRateToUpUseCase
import com.example.currencyconverter.domain.useCase.SetAccountUseCase
import com.example.currencyconverter.domain.useCase.SetTransactionUseCase
import com.example.converterrate.ui.mapper.AccountDomainToAccountUIMapper
import com.example.converterrate.ui.mapper.AccountUIToAccountDomainMapper
import com.example.converterrate.ui.mapper.RateDomainToRateUIMapper
import com.example.converterrate.ui.mapper.RateUIToRateDomainMapper
import com.example.converterrate.ui.mapper.TransactionDomainToTransactionUIMapper
import com.example.converterrate.ui.mapper.TransactionUIToTransactionDomainMapper
import com.example.converterrate.ui.models.AccountUI
import com.example.converterrate.ui.models.ChangeUI
import com.example.converterrate.ui.utils.ModeRateScreen
import com.example.converterrate.ui.models.RateUI
import com.example.converterrate.ui.models.TransactionUI
import com.example.converterrate.ui.utils.Screen
import com.example.currencyconverter.domain.useCase.IsFirstLaunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * @author d.borodin
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRateUseCase: GetRateUseCase,
    private val moveSelectedRateToUpUseCase: MoveSelectedRateToUpUseCase,
    private val rateDomainToRateUIMapper: RateDomainToRateUIMapper,
    private val rateUIToRateDomainMapper: RateUIToRateDomainMapper,
    private val transactionDomainToTransactionUIMapper: TransactionDomainToTransactionUIMapper,
    private val transactionUIToTransactionDomainMapper: TransactionUIToTransactionDomainMapper,
    private val accountDomainToAccountUIMapper: AccountDomainToAccountUIMapper,
    private val accountUIToAccountDomainMapper: AccountUIToAccountDomainMapper,
    private val getAccountUseCase: GetAccountUseCase,
    private val setAccountUseCase: SetAccountUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
    private val setTransactionUseCase: SetTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    isFirstLaunchUseCase: IsFirstLaunchUseCase,
): ViewModel() {

    val stateModeRateScreen = mutableStateOf<ModeRateScreen>(ModeRateScreen.LIST)
    val stateCurrentScreen = mutableStateOf(Screen.RATE)

    val stateCurrentRate = mutableStateOf(RateUI.defaultRate)
    val stateListRate = mutableStateOf<List<RateUI>>(emptyList())
    val stateFilteredListRate = mutableStateOf<List<RateUI>>(emptyList())
    val stateListAccount = mutableStateOf<List<AccountUI>>(emptyList())

    val stateListTransaction = mutableStateOf<List<TransactionUI>>(emptyList())


    val stateTitleAppBar = mutableStateOf<String>("")

    val stateCurrentAccountTextField = mutableStateOf(0.0)

    val stateCurrentChange = mutableStateOf(ChangeUI())

    init {
        if(isFirstLaunchUseCase.invoke()){
            setInitialAccount()
        }
        updateListRates()
        updateAccount()
        updateTitleAppBar()
        updateTransaction()
    }

    fun updateAccount() {
        viewModelScope.launch {
            getAccountUseCase.invoke().collect { listAccount ->
                stateListAccount.value = listAccount.map { accountDomainToAccountUIMapper(it) }
                setBalance()
            }
        }
    }

    private fun setBalance() {
        var list = stateListRate.value.map { rate ->
            val account = stateListAccount.value.firstOrNull { account -> account.code == rate.currency }
            rate.copy(balance = account?.amount ?: 0.0)
        }
        stateListRate.value = list
    }

    suspend fun setAccount(account: AccountDomain) {
        setAccountUseCase.invoke(account)
    }

    fun updateListRates() {
        viewModelScope.launch {
            while (true){
                if(stateModeRateScreen.value == ModeRateScreen.LIST) {
                    val rates = getRateUseCase.invoke(
                        code = stateCurrentRate.value.currency,
                        amount = stateCurrentRate.value.value
                    )
                    val sortedList  = moveSelectedRateToUpUseCase.invoke(
                        selectedRate = rateUIToRateDomainMapper(stateCurrentRate.value),
                        list = rates,
                    )
                    if(stateModeRateScreen.value == ModeRateScreen.LIST){
                        stateListRate.value = sortedList.map { rateDomainToRateUIMapper(it) }
                    }
                    setBalance()
                }
                delay(1000)
            }
        }
    }

    fun updateRate(rate: RateUI, value: Double = 1.0) {

        stateCurrentAccountTextField.value = value
        stateCurrentRate.value = rate.copy(value = value)
        val ratesDomain = stateListRate.value.map { rateUIToRateDomainMapper(it) }
        var sortedList  = moveSelectedRateToUpUseCase.invoke(
            selectedRate = rateUIToRateDomainMapper(stateCurrentRate.value),
            list = ratesDomain,
        )

        var listRateUI = sortedList.map { rateDomainToRateUIMapper(it) }
        stateListRate.value = listRateUI

        setBalance()
        filterList()
    }

    private fun filterList(){
        Log.d("TAG", "current mode = ${stateModeRateScreen.value}")
        if(stateModeRateScreen.value == ModeRateScreen.TRANSACTION){
            Log.d("TAG", "filter list = ${stateModeRateScreen.value}")
            stateFilteredListRate.value = listOf(stateListRate.value.first()) + stateListRate.value.filter{
                Log.d("TAG", "balance = ${it.balance} value = ${it.value}")
                Log.d("TAG", "it.balance >= it.value && it != listRateUI.first() = ${it.balance >= it.value}")
                it.balance >= (it.value * stateCurrentAccountTextField.value) && it != stateListRate.value.first()
            }.map { it.copy(totalAccount = stateCurrentAccountTextField.value * it.value) }
        }
    }

    private fun updateTransaction() {
        viewModelScope.launch {
            getTransactionUseCase.invoke().collect { transactions ->
                stateListTransaction.value = transactions.map { transactionDomainToTransactionUIMapper(it) }
            }
        }
    }

    fun deleteTransaction(transaction: TransactionUI){
        val transactionDomain = transactionUIToTransactionDomainMapper(transaction)
        viewModelScope.launch {
            deleteTransactionUseCase.invoke(transactionDomain)
        }
    }

    fun setTransaction() {

        val fromRate = stateCurrentChange.value.minusRate ?: return
        val toRate = stateCurrentChange.value.plusRate ?: return
        val localDateTime = LocalDateTime.now()
        val transactionUI = TransactionUI(
            from = fromRate.currency,
            to = toRate.currency,
            fromAmount = fromRate.totalAccount,
            toAmount = toRate.value,
            dateTime = localDateTime,
        )

        Log.d("TAG", "TRANSACTION")
        viewModelScope.launch {
            setTransactionUseCase.invoke(transactionUIToTransactionDomainMapper(transactionUI))
            val accountUIFirst = AccountUI(
                code = fromRate.currency,
                amount = fromRate.balance - fromRate.totalAccount
            )
            val accountUISecond = AccountUI(
                code = toRate.currency,
                amount = toRate.balance + toRate.value
            )
            setAccount(accountUIToAccountDomainMapper(accountUIFirst))
            setAccount(accountUIToAccountDomainMapper(accountUISecond))
        }
    }

    private fun setInitialAccount() {
        val scope = CoroutineScope(context = Dispatchers.IO)
        scope.launch {
            getRateUseCase.invoke(
                code = stateCurrentRate.value.currency,
                amount = stateCurrentRate.value.value
            ).forEach {
                launch {
                    val amount = if(it.currency == "RUB") 75000.0 else 0.0
                    val account = AccountDomain(code = it.currency, amount = amount)
                    setAccount(account)
                }
            }
        }
    }

    fun updateMode(isFocused: Boolean){

        val mode = if(isFocused) ModeRateScreen.TRANSACTION else ModeRateScreen.LIST
        stateModeRateScreen.value = mode
        updateRate(stateCurrentRate.value)
        updateTitleAppBar()
    }

    private fun updateTitleAppBar(){
        when(stateCurrentScreen.value){
            Screen.RATE -> {
                stateTitleAppBar.value = when(stateModeRateScreen.value){
                    ModeRateScreen.LIST -> "Валюты: список"
                    ModeRateScreen.TRANSACTION -> "Валюты: ввод суммы"
                }
            }
            Screen.CHANGE -> { stateTitleAppBar.value = "Обмен" }
            Screen.TRANSACTION -> { stateTitleAppBar.value = "Транзакции" }
        }



    }

    fun navigateToRateScreen() {
        stateCurrentScreen.value = Screen.RATE
        updateTitleAppBar()
    }

    fun navigateToChangeScreen(rate: RateUI) {
        stateCurrentChange.value = ChangeUI(stateCurrentRate.value, rate)
        stateCurrentScreen.value = Screen.CHANGE
        updateTitleAppBar()
    }
    fun navigateToTransactionScreen() {
        stateCurrentScreen.value = Screen.TRANSACTION
        updateTitleAppBar()
    }
}


