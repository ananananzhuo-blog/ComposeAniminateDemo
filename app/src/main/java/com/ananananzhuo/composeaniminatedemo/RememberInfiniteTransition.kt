package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun rememberInfiniteTransition1() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .fillMaxSize(0.8f)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "公众号：安安安安卓 原创，禁抄袭",
                style = TextStyle(color = Color.White, fontSize = 30.sp)
            )
        }
    }
}