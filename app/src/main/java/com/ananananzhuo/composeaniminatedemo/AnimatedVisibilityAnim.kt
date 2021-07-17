package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * author  :mayong
 * function:
 * date    :2021/7/14
 **/

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun animatedVisiblity() {
    val visible = remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedVisibility(visible = visible.value, enter = fadeIn(1f), exit = fadeOut(0.4f)) {
            Text(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Red), text = ""
            )
        }
        Button(onClick = {
            visible.value = !visible.value
        }) {
            Text(text = "开始动画")
        }
    }
}