package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * author  :mayong
 * function:
 * date    :2021/7/16
 **/

@Composable
fun animTargetBasedAnimation() {
    var state by remember {
        mutableStateOf(0)
    }
    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(2000),
            typeConverter = Float.VectorConverter,
            initialValue = 100f,
            targetValue = 300f
        )
    }
    var playTime by remember { mutableStateOf(0L) }
    var animationValue by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(state) {
        val startTime = withFrameNanos { it }
        println("进入协程：")
        do {
            playTime = withFrameNanos { it } - startTime
            animationValue = anim.getValueFromNanos(playTime).toInt()
        } while (!anim.isFinishedFromNanos(playTime))

    }
    Box(modifier = Modifier.fillMaxSize(1f),contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .size(animationValue.dp)
            .background(Color.Red,shape = RoundedCornerShape(animationValue/5))
            .clickable {
                state++
            },contentAlignment = Alignment.Center) {
            Text(text = animationValue.toString(),style = TextStyle(color = Color.White,fontSize = (animationValue/5).sp))
        }
    }
}
