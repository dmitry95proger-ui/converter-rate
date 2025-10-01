package com.example.converterrate.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.converterrate.ui.theme.ColorText
import com.example.converterrate.ui.theme.GreenBackgroundLight
import com.example.converterrate.ui.theme.GreenLight
import com.example.converterrate.ui.utils.Utils
import com.example.converterrate.ui.viewmodel.MainViewModel

/**
 * @author d.borodin
 */

@Composable
fun ChangeScreen(viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp)
            .background(color = GreenLight)
    ) {
        val change = viewModel.stateCurrentChange.value
        Column {
            ItemChange(change.plusRate, "+")
            ItemChange(change.minusRate, "-")
            Box(Modifier.fillMaxWidth().height(200.dp)){
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = GreenBackgroundLight),
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        viewModel.setTransaction()
                        viewModel.navigateToRateScreen()
                    },
                ){
                    Text(
                        text = "By ${Utils.getDescription(change.plusRate?.currency!!)} for ${Utils.getDescription(change.minusRate?.currency!!)}",
                        color = ColorText,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}