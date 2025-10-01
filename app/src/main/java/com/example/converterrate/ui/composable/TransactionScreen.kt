package com.example.converterrate.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.converterrate.ui.theme.ColorText
import com.example.converterrate.ui.theme.GreenLight
import com.example.converterrate.ui.viewmodel.MainViewModel

/**
 * @author d.borodin
 */

@Composable
fun TransactionScreen(viewModel: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GreenLight)
            .padding(start = 5.dp, end = 5.dp)
    ) {
        val list = viewModel.stateListTransaction.value
        if(list.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Транзакций нет",
                    color = ColorText,
                    fontSize = 30.sp
                )
            }
        }
        else {
            LazyColumn {
                itemsIndexed(list) { index, item ->
                    ItemTransaction(
                        transaction = item,
                        onDeleteTransaction = { transaction ->
                            viewModel.deleteTransaction(
                                transaction
                            )
                        }
                    )
                }
            }
        }
    }
}