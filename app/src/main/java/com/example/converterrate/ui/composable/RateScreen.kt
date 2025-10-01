package com.example.converterrate.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.converterrate.ui.utils.ModeRateScreen
import com.example.converterrate.ui.theme.GreenLight
import com.example.converterrate.ui.viewmodel.MainViewModel

/**
 * @author d.borodin
 */

@Composable
fun RateScreen(viewModel: MainViewModel) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = GreenLight)
        .padding(start = 5.dp, end = 5.dp)
    ){
        LazyColumn {
            val list =
                if(viewModel.stateModeRateScreen.value == ModeRateScreen.LIST) viewModel.stateListRate.value
                else viewModel.stateFilteredListRate.value
            itemsIndexed(list) { index, item ->
                ItemRate(
                    rate = item,
                    mode = viewModel.stateModeRateScreen.value,
                    isSelectedItem = index == 0,
                    navigateToChangesScreen = { rate -> viewModel.navigateToChangeScreen(rate) },
                    onChangeMode = { isFocused -> viewModel.updateMode(isFocused) },
                    updateAccount = { viewModel.updateRate(item, it.toInt().toDouble()) }
                ) { rate ->
                    viewModel.updateRate(rate)
                }
            }
        }
    }
}