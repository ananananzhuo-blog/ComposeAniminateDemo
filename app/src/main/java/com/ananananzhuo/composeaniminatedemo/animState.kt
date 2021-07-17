package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.animation.core.animateRectAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*

/**
 * author  :mayong
 * function:
 * date    :2021/7/14
 **/

@Composable
fun animSize() {
    val enable = remember {
        mutableStateOf(true)
    }
    val size =
        animateSizeAsState(targetValue = if (enable.value) Size(50f, 50f) else Size(300f, 300f))
    Column(
        modifier = Modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(size.value.width.dp, size.value.height.dp)
                .clickable {
                    enable.value = !enable.value
                },
            painter = painterResource(id = R.drawable.apple),
            contentDescription = ""
        )
    }
}

@Composable
fun animColor() {
    var enable by remember {
        mutableStateOf(true)
    }
    val colors = animateColorAsState(targetValue = if (enable) Color.Green else Color.Red)
    val size = animateIntSizeAsState(
        targetValue = if (enable) IntSize(100, 100) else IntSize(
            300,
            300
        )
    )
    Column(
        modifier = Modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(size.value.width.dp, size.value.height.dp)
                .height(400.dp)
                .background(
                    color = colors.value,
                    shape = if (enable) RectangleShape else CircleShape
                )
                .clickable {
                    enable = !enable
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "公众号：安安安安安卓原创，禁抄袭")
        }
    }
}

//@Composable
//fun animBounds(){
//}