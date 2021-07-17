package com.ananananzhuo.composeaniminatedemo

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.reflect.KProperty
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
/**
 * author  :mayong
 * function:
 * date    :2021/7/15
 **/

@Composable
fun animupdateTransition() {
    var state by remember {
        mutableStateOf(BoxState.Collapsed)
    }
    val transition = updateTransition(targetState = state, label = "")

    val round = transition.animateDp(label = "") {
        when (it) {
            BoxState.Collapsed -> 40.dp
            BoxState.Expanded -> 100.dp
        }
    }
    val color = transition.animateColor(label = "") {
        when (it) {
            BoxState.Collapsed -> Color.Red
            BoxState.Expanded -> Color.Green
        }
    }
    Box(Modifier.fillMaxSize(1f),contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    color.value,
                    shape = RoundedCornerShape(corner = CornerSize(round.value))
                )
                .clickable {
                    state =
                        if (state == BoxState.Collapsed) BoxState.Expanded else BoxState.Collapsed
                },contentAlignment = Alignment.Center
        ) {
            Text(text = "点击开始动画",style = TextStyle(color = Color.White,fontSize = 20.sp))
        }
    }
}
private enum class BoxState {
    Collapsed,
    Expanded
}