package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * author  :mayong
 * function:
 * date    :2021/7/17
 **/

@Composable
fun animEasing() {
    var state by remember {
        mutableStateOf(true)
    }
    var currentEasing by remember {
        mutableStateOf(LinearEasing)
    }
    val value by animateIntAsState(
        targetValue = if (state) 300 else 100,
        animationSpec = tween(durationMillis = 500, easing = currentEasing)
    )
    Column {
        checkEasing() {
            currentEasing = it
            state=!state
        }
      Box (Modifier.fillMaxSize(1f),contentAlignment = Alignment.CenterStart){
          Box(
              Modifier
                  .width(value.dp)
                  .height(60.dp)
                  .background(Color.Red),contentAlignment = Alignment.CenterStart
          ) {
                Text(text = "公众号：安安安安卓 原创 禁抄袭",style = TextStyle(color = Color.White),fontSize = 20.sp)
          }
      }
    }

}

@Composable
fun checkEasing(func: (state: Easing) -> Unit) {
    val easings = remember {
        mutableStateListOf(
            FastOutSlowInEasing,
            LinearOutSlowInEasing,
            FastOutLinearInEasing,
            LinearEasing,
            CubicBezierEasing(10f, 200f, 500f, 600f)
        )
    }
    var selectItem by remember {
        mutableStateOf(easings[0])
    }
    Column(
        modifier = Modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        easings.forEach {
            Box(
                Modifier
                    .width(260.dp)
                    .height(60.dp)
                    .clickable {
                        selectItem = it
                        func(it)
                    }
                    .padding(top = 5.dp)
                    .background(
                        color = if (selectItem == it) Color.Red else Color.Green,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(start = 10.dp),
                contentAlignment = Alignment.CenterStart


            ) {
                Text(
                    text = it.toString(),
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}