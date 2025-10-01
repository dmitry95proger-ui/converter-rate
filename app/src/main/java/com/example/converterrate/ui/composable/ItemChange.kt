package com.example.converterrate.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.converterrate.ui.models.RateUI
import com.example.converterrate.ui.theme.ColorText
import com.example.converterrate.ui.theme.GreenBackgroundLight
import com.example.converterrate.ui.utils.Utils

/**
 * @author d.borodin
 */

@Composable
fun ItemChange(rate: RateUI?, prefix: String) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .padding(top = 10.dp)
            .background(color = GreenBackgroundLight, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
    ){
        Row {
            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(Utils.getImage(rate?.currency ?: throw IllegalArgumentException("Illegal resource"))),
                contentDescription = null,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
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
                    val textAccount = "$prefix${if(prefix == "-") RateUI.convertDouble(rate.totalAccount) else rate.value}"
                    Box(Modifier
                        .weight(0.5f)
                        .fillMaxHeight()){
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = textAccount,
                            fontSize = 20.sp,
                            color = if(prefix == "-") Color.Red else ColorText
                        )
                    }
                }
                Text(
                    modifier = Modifier.align(Alignment.BottomStart),
                    text = "Balance ${rate.balance}",
                    fontSize = 20.sp,
                    color = ColorText
                )
            }
        }
    }
}