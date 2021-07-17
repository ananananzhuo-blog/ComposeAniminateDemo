package com.ananananzhuo.composeaniminatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ananananzhuo.composeaniminatedemo.ui.theme.ComposeAniminateDemoTheme
import java.io.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAniminateDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

const val animatedvisiblity = "animatedvisiblity"
const val animatedContentSize = "animatedContentSize"
const val animSize = "animSize"
const val animColor = "animColor"
const val animChangeColor = "animChangeColor"
const val animupdatetransition = "animupdatetransition"
const val rememberInfiniteTransition1 = "rememberInfiniteTransition1"
const val animTargetBasedAnimation = "animTargetBasedAnimation"
const val animAnimationSpec = "animAnimationSpec"
const val animTweenSpec = "animTweenSpec"
const val animkeyframesSpec = "animkeyframesSpec"
const val animrepeatableSpec = "animrepeatableSpec"
const val animinfiniteRepeatableSpec = "animinfiniteRepeatableSpec"
const val animsnapSpec = "animsnapSpec"
const val animEasing = "animEasing"
const val animAnimationVector = "animAnimationVector"

@Composable
fun Greeting() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = "list") {
        composable("list") {
            ListView(controller)
        }
        composable(animatedvisiblity) {
            animatedVisiblity()
        }
        composable(animatedContentSize) {
            animContentSize()
        }
        composable(animSize) {
            animSize()
        }
        composable(animColor) {
            animColor()
        }
        composable(animChangeColor) {
            animChangeColor()
        }
        composable(animupdatetransition) {
            animupdateTransition()
        }
        composable(rememberInfiniteTransition1) {
            rememberInfiniteTransition1()
        }
        composable(animTargetBasedAnimation) {
            animTargetBasedAnimation()
        }
        composable(animAnimationSpec) {
            animAnimationSpec(controller)
        }

        composable(springSpec) {
            animSpring()
        }
        composable(animTweenSpec) {
            animTweenSpec()
        }
        composable(animkeyframesSpec) {
            animkeyframesSpec()
        }
        composable(animrepeatableSpec) {
            animrepeatableSpec()
        }
        composable(animinfiniteRepeatableSpec) {
            animinfiniteRepeatableSpec()
        }
        composable(animsnapSpec) {
            animsnapSpec()
        }
        composable(animEasing) {
            animEasing()
        }
        composable(animAnimationVector) {
            animAnimationVector()
        }
    }
}

@Composable
fun ListView(controller: NavHostController) {
    Column(modifier = Modifier.verticalScroll(state = ScrollState(0))) {
        item("AnimatedVisiblity动画") {
            controller.navigate(animatedvisiblity)
        }
        item("AnimatedContentSize动画") {
            controller.navigate(animatedContentSize)
        }
        item(text = "图片放大缩小animate\\*AsState") {
            controller.navigate(animSize)
        }
        item(text = "颜色更改动画animate\\*AsState") {
            controller.navigate(animColor)
        }
        item(text = "Animatable实现抛物线效果") {
            controller.navigate(animChangeColor)
        }
        item(text = "updateTransition动画") {
            controller.navigate(animupdatetransition)
        }
        item(text = "rememberInfiniteTransition动画") {
            controller.navigate(rememberInfiniteTransition1)
        }
        item(text = "animTargetBasedAnimation实现正方形变圆，文字膨胀") {
            controller.navigate(animTargetBasedAnimation)
        }
        item(text = "animAnimationSpec介绍") {
            controller.navigate(animAnimationSpec)
        }
        item(text = "animEasing属性介绍") {
            controller.navigate(animEasing)
        }
        item(text = "使用AnimationVector实现颜色动画") {
            controller.navigate(animAnimationVector)
        }
    }

}

@Composable
fun item(text: String, click: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(1f)
                .background(Color.Red, shape = RoundedCornerShape(10.dp))
                .clickable(enabled = true, onClick = click)
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                painter = painterResource(id = R.drawable.apple),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = text,
                style = TextStyle(color = Color.White),
                fontSize = 20.sp
            )
        }
        Divider(modifier = Modifier.padding(vertical = 3.dp))
    }
}