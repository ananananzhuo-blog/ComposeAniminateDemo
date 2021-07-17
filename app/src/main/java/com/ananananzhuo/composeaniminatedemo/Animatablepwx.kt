package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * author  :mayong
 * function:
 * date    :2021/7/15
 **/


@Composable
fun animChangeColor() {
    val color = remember {
        Animatable(Color.Red)
    }
    val state = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(state.value) {
        color.animateTo(if (state.value) Color.Red else Color.Magenta)
    }
    Box(Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(color.value, shape = RoundedCornerShape(30.dp))
                .size(200.dp)
                .clickable {
                    state.value = !state.value
                }, contentAlignment = Alignment.Center
        ) {
            Text(
                text = "公众号：安安安安卓 原创，禁抄袭",
                style = TextStyle(color = Color.White, fontSize = 15.sp)
            )
        }
    }
}