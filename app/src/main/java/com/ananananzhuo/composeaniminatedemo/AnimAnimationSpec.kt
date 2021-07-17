package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.reflect.KProperty

/**
 * author  :mayong
 * function:
 * date    :2021/7/16
 **/

const val springSpec = "springSpec"

@Composable
fun animAnimationSpec(controller: NavHostController) {
    Column {
        item(text = "spring") {
            controller.navigate(springSpec)
        }
        item(text = "animTweenSpec") {
            controller.navigate(animTweenSpec)
        }
        item(text = "animkeyframesSpec") {
            controller.navigate(animkeyframesSpec)
        }
        item(text = "animrepeatableSpec") {
            controller.navigate(animrepeatableSpec)
        }
        item(text = "animinfiniteRepeatableSpec") {
            controller.navigate(animinfiniteRepeatableSpec)
        }
        item(text = "animsnapSpec") {
            controller.navigate(animsnapSpec)
        }
    }
}

/**
 * 设置弹簧动画
 */
@Composable
fun animSpring() {
    val state = remember {
        mutableStateOf(true)
    }
    var value = animateIntAsState(
        targetValue = if (state.value) 300 else 100,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )

    Box(
        Modifier
            .fillMaxSize(1f)
            .padding(start = 30.dp), contentAlignment = Alignment.CenterStart
    ) {
        Box(
            Modifier
                .width(value.value.dp)
                .height(80.dp)
                .background(Color.Red, RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp))
                .clickable {
                    state.value = !state.value
                }, contentAlignment = Alignment.CenterStart
        ) {
            Text(text = "哈哈哈", style = TextStyle(color = Color.White, fontSize = 20.sp))
        }
    }
}

/**
 * tween可以设置动画时长
 */
@Composable
fun animTweenSpec() {
    val state = remember {
        mutableStateOf(true)
    }
    val value = animateIntAsState(
        targetValue = if (state.value) 300 else 100,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = 200,
            easing = LinearEasing
        )
    )

    Box(
        Modifier
            .fillMaxSize(1f)
            .padding(start = 50.dp), contentAlignment = Alignment.CenterStart
    ) {
        Box(
            Modifier
                .width(value.value.dp)
                .height(100.dp)
                .background(Color.Red, RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp))
                .clickable {
                    state.value = !state.value
                }
        ) {

        }
    }

}

/**
 * 分段设置animationSpec
 */
@Composable
fun animkeyframesSpec() {
    var state by remember {
        mutableStateOf(true)
    }
    val value by animateIntAsState(
        targetValue = if (state) 300 else 100,
        animationSpec = keyframes {
            durationMillis = 2000
            0 at 700 with LinearOutSlowInEasing
            700 at 1400 with FastOutLinearInEasing
            1400 at 2000
        })

    Box(Modifier.fillMaxSize(1f), contentAlignment = Alignment.CenterStart) {
        Box(
            Modifier
                .width(value.dp)
                .height(100.dp)
                .background(Color.Red, RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp))
                .clickable {
                    state = !state
                }
        ) {

        }
    }
}

/**
 * 有限次数的执行动画
 */
@Composable
fun animrepeatableSpec() {
    var state by remember {
        mutableStateOf(true)
    }
    val value by animateIntAsState(
        targetValue = if (state) 300 else 100,
        animationSpec = repeatable(
            iterations = 5,//动画重复执行的次数，设置多少就执行多少次
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier
            .fillMaxSize(1f)
            .padding(start = 30.dp), contentAlignment = Alignment.CenterStart) {
        Box(
            Modifier
                .width(value.dp)
                .height(100.dp)
                .background(Color.Red, RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp))
                .clickable {
                    state = !state
                }
        ) {

        }
    }
}

/**
 * 无限次数的执行动画
 */
@Composable
fun animinfiniteRepeatableSpec() {
    var state by remember {
        mutableStateOf(true)
    }
    val value by animateIntAsState(
        targetValue = if (state) 300 else 100,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier
            .fillMaxSize(1f)
            .padding(start = 30.dp), contentAlignment = Alignment.CenterStart) {
        Box(
            Modifier
                .width(value.dp)
                .height(100.dp)
                .background(Color.Red, RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp))
                .clickable {
                    state = !state
                }
        ) {
            Text(text = "公众号：安安安安卓 原创，禁转载")
        }
    }
}

@Composable
fun animsnapSpec() {

}