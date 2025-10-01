package com.example.converterrate.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.converterrate.ui.models.RateUI
import com.example.converterrate.ui.theme.ColorText
import com.example.converterrate.ui.theme.GreenBackgroundLight
import com.example.converterrate.ui.utils.Utils
import com.example.converterrate.ui.utils.ModeRateScreen

/**
 * @author d.borodin
 */

@Composable
fun ItemRate(
    rate: RateUI,
    mode: ModeRateScreen,
    isSelectedItem: Boolean,
    navigateToChangesScreen: (RateUI) -> Unit,
    onChangeMode: (Boolean) -> Unit,
    updateAccount: (String) -> Unit,
    onSelectedRate: (RateUI) -> Unit,

    ) {

    val valueTextField = remember { mutableStateOf("1") }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .height(100.dp)
            .padding(top = 10.dp)
            .background(color = GreenBackgroundLight, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .clickable {
                when(mode){
                    ModeRateScreen.LIST -> {
                        onSelectedRate(rate)
                        valueTextField.value = "1"
                    }
                    ModeRateScreen.TRANSACTION -> {
                        if(!isSelectedItem) navigateToChangesScreen(rate)
                    }
                }
            }
    ){
        Row {
            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(Utils.getImage(rate.currency)),
                contentDescription = null,
            )
            Box(
                modifier = Modifier.fillMaxSize().padding(start = 5.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                        modifier = Modifier.fillMaxHeight().weight(0.5f)
                    ){
                        Text(
                            text = rate.currency,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = Utils.getDescription(rate.currency),
                            fontSize = 20.sp
                        )
                    }
                    Box(Modifier.weight(0.5f).fillMaxHeight()){

                        if(isSelectedItem){
                            Row(Modifier.align(Alignment.Center)) {
                                TextField(
                                    modifier = Modifier
                                        .onFocusChanged { focusState ->
                                            onChangeMode(focusState.isFocused)
                                            if(!focusState.isFocused) valueTextField.value = "1"
                                        }.weight(0.6f)
                                        .height(50.dp),
                                    maxLines = 1,
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                    value = valueTextField.value,
                                    onValueChange = {
                                        if (it.all { char -> char.isDigit() }) {
                                            valueTextField.value = it
                                            if(it == "0" || it == "") valueTextField.value = "1"
                                            updateAccount(valueTextField.value)
                                        }
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = ColorText.copy(alpha = 0.2f),
                                        unfocusedContainerColor = ColorText.copy(alpha = 0.2f),
                                    )
                                )
                                if(mode == ModeRateScreen.TRANSACTION){
                                    IconButton(
                                        modifier = Modifier.weight(0.4f),
                                        onClick = {
                                            focusManager.clearFocus(force = true)
                                        }
                                    ) {
                                        Icon(
                                            modifier = Modifier,
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null,
                                            tint = ColorText
                                        )
                                    }
                                }
                                else Spacer(Modifier.weight(0.4f))
                            }
                        }
                        else{
                            val textAccount =
                                if(mode == ModeRateScreen.TRANSACTION)
                                    (RateUI.convertDouble(rate.totalAccount)).toString() else rate.value.toString()
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = textAccount,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                Text(
                    modifier = Modifier.align(Alignment.BottomStart),
                    text = "Balance ${RateUI.convertDouble(rate.balance)}",
                    fontSize = 20.sp,
                    color = ColorText
                )
            }
        }
    }
}