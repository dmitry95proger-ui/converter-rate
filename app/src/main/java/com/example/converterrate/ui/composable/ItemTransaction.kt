package com.example.converterrate.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.converterrate.ui.theme.ColorText
import com.example.converterrate.ui.theme.GreenBackgroundLight
import com.example.converterrate.ui.models.TransactionUI
import com.example.converterrate.ui.theme.ColorText2
import com.example.converterrate.ui.utils.Utils

/**
 * @author d.borodin
 */

@Composable
fun ItemTransaction(
    transaction: TransactionUI,
    onDeleteTransaction: (TransactionUI) -> Unit,
    ) {

    Box(
        modifier = Modifier
            .height(150.dp)
            .padding(top = 7.dp)
            .background(color = GreenBackgroundLight, shape = RoundedCornerShape(30.dp))
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp)
            ){
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        Row {
                            Text(
                                text = transaction.from,
                                color = ColorText,
                                fontSize = 30.sp
                            )
                            Spacer(Modifier.size(5.dp))
                            Image(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(Utils.getImage(transaction.from)),
                                contentDescription = null,
                            )
                        }
                        Text(
                            text = TransactionUI.convertDouble(transaction.fromAmount).toString(),
                            color = ColorText,
                            fontSize = 25.sp
                        )
                    }
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = ColorText
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(5.dp)
            ){
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = ColorText
                    )
                    Column {
                        Row {
                            Text(
                                text = transaction.to,
                                color = ColorText,
                                fontSize = 30.sp
                            )
                            Spacer(Modifier.size(5.dp))
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(Utils.getImage(transaction.to)),
                                contentDescription = null,
                            )
                        }
                        Text(
                            text = TransactionUI.convertDouble(transaction.toAmount).toString(),
                            color = ColorText,
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {
                onDeleteTransaction(transaction)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Red
            )
        }
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = TransactionUI.convertDate(transaction.dateTime),
            color = ColorText2,
            fontSize = 20.sp
        )
    }
}