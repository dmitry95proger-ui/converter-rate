package com.example.converterrate.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.converterrate.ui.theme.ColorText
import com.example.converterrate.ui.utils.Screen
import com.example.converterrate.ui.viewmodel.MainViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.converterrate.ui.utils.Utils

/**
 * @author d.borodin
 */

@Composable
fun TopBar(viewModel: MainViewModel) {
    val currentScreen = viewModel.stateCurrentScreen.value
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.align(Alignment.BottomStart).padding(5.dp),
        ){
            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = {
                    if(currentScreen == Screen.RATE)
                        viewModel.navigateToTransactionScreen()
                    else
                        viewModel.navigateToRateScreen()
                }
            ) {
                Icon(
                    imageVector = if(currentScreen == Screen.RATE) Icons.Default.Person else Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = ColorText
                )
            }
            Spacer(Modifier.size(10.dp))
            Text(
                text = viewModel.stateTitleAppBar.value,
                color = ColorText,
                fontSize = 25.sp
            )
        }
        if(viewModel.stateCurrentScreen.value == Screen.RATE) {
            Box(
                modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp).size(40.dp)
            ){
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(Utils.getImage(viewModel.stateCurrentRate.value.currency)),
                    contentDescription = null,
                )
            }
        }
    }
}