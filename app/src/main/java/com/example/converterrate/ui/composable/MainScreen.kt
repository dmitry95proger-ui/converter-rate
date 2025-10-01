package com.example.converterrate.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.converterrate.ui.theme.GreenBackgroundLight
import com.example.converterrate.ui.utils.Screen
import com.example.converterrate.ui.viewmodel.MainViewModel

/**
 * @author d.borodin
 */

@Composable
fun MainScreen(viewModel: MainViewModel){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Box(
                modifier = Modifier.weight(0.12f).background(GreenBackgroundLight)
                //modifier = Modifier.height(200.dp).background(GreenBackgroundLight)
            ){
                TopBar(viewModel)
            }
            Box(
                modifier = Modifier.weight(0.88f)
                //modifier = Modifier.fillMaxHeight()
            ){
                when(viewModel.stateCurrentScreen.value) {
                    Screen.RATE -> {
                        RateScreen(viewModel)
                    }
                    Screen.CHANGE -> {
                        ChangeScreen(viewModel)
                    }
                    Screen.TRANSACTION -> {
                        TransactionScreen(viewModel)
                    }
                }
            }
        }
    }
}