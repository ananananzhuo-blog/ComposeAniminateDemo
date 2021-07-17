package com.ananananzhuo.composeaniminatedemo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * author  :mayong
 * function:
 * date    :2021/7/17
 **/

@Composable
fun animAnimationVector() {
    var state by remember {
        mutableStateOf(true)
    }
    val value by animateValueAsState(
        targetValue = if (state) AnimSize(0xffff5500, 100f) else AnimSize(0xff00ff00, 300f),
        typeConverter = TwoWayConverter(
            convertToVector = {
//                AnimationVector2D(target.color.toFloat(), target.size)
                AnimationVector2D(it.color.toFloat(), it.size)
            },
            convertFromVector = {
                AnimSize(it.v1.toLong(), it.v2)
            }
        )
    )
    println("颜色:${value.color}")
    Box(modifier = Modifier.fillMaxSize(1f).padding(30.dp), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(value.size.dp)
//                .size(300.dp)
                .background(Color(value.color), RoundedCornerShape(30.dp))
                .clickable {
                    state = !state
                }
        ) {

        }
    }
}

data class AnimSize(val color: Long, val size: Float)