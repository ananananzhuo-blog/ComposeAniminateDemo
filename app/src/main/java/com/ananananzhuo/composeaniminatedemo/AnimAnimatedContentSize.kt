package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * author  :mayong
 * function:
 * date    :2021/7/14
 **/

@Composable
fun animContentSize() {
    var message = remember { mutableStateOf("Hello") }
   Column {
       Button(onClick = {
           message.value="hello world开启动画"
       }) {
           Text(text = "开始动画")
       }
       Text(modifier = Modifier
           .size(100.dp)
           .background(Color.Red)
           .animateContentSize(), text = message.value)

   }
}