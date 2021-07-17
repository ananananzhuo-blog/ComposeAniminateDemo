关注公众号学习更多知识

![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)
## 高级动画

高级动画一般指封装性较高的动画，使用较为简单，所以不进行讲解，主要有以下三种

1. AnimatedVisibility

2. animateContentSize

3. Crossfade

## 低级别动画 API

### animate*AsState
所能处理属性的种类：Float、Color、Dp、Size、Bounds、Offset、Rect、Int、IntOffset 和 IntSize

通过animate*AsState我们可以实现单一属性的动画效果,我们只需要提供目标值就可以自动从当前进度动画过渡到目标值

#### 实现放大动画
1. 代码

```
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
```
2. 实现效果

![](https://files.mdnice.com/user/15648/d7707612-1f34-4a66-865e-b28fd50b925a.gif)
#### 实现颜色变化动画
1. 代码

```

@Composable
fun animColor() {
    val enable = remember {
        mutableStateOf(true)
    }
    val colors = animateColorAsState(targetValue = if (enable.value) Color.Green else Color.Red)
    val size = animateIntSizeAsState(
        targetValue = if (enable.value) IntSize(100, 100) else IntSize(
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
                    shape = if (enable.value) RectangleShape else CircleShape
                )
        ) {

        }
    }
}
```
2. 效果

![](https://files.mdnice.com/user/15648/51abf1a1-4480-41f0-9e7b-d540c5e38af1.gif)


### 使用Animatable实现颜色变化效果

Animatable 是一个值容器，我们可以通过调用animateTo实现动画效果。动画执行过程中如果再次开启动画会中断当前动画。

Animatable动画执行过程中值的变化是在协程中执行的，所以animateTo是一个挂起操作

1. 代码

```
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
                text = "颜色动画",
                style = TextStyle(color = Color.White, fontSize = 40.sp)
            )
        }
    }
}
```
2. 效果
![](https://files.mdnice.com/user/15648/56e26e7b-9337-413a-ae29-6eb73792ce73.gif)


### 使用updateTransition实现颜色和圆角动画

使用updateTransition可以实现多个动画组合的效果。

例如：我们可以再动画执行过程中同时执行大小和颜色变化效果

本例中我们定义了一个枚举用来控制动画，枚举可以定义多个分别对应动画的多个状态

1. 代码

```
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
```
2. 效果
![](https://files.mdnice.com/user/15648/e970ebd7-64b6-4e26-b391-1c7de21e5c10.gif)


### rememberInfiniteTransition

rememberInfiniteTransition的使用和updateTransition基本一样，不同的是rememberInfiniteTransition的动画一旦开始便会一直反复运行下去，只有被移除动画才能结束
1. 代码

```
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
```
2. 效果
![](https://files.mdnice.com/user/15648/3f1046df-d0cf-48ca-9d90-f422619b0a21.gif)


### TargetBasedAnimation
TargetBasedAnimation可以控制动画的执行时间，还可以延迟一段时间再开启动画。
1. 代码

```
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

```
2. 效果
![](https://files.mdnice.com/user/15648/29155ab0-71bb-4a61-945f-7c89a3f88f5c.gif)


## 自定义动画

### AnimationSpec
AnimationSpec可以自定义动画的行为，效果类似于原生动画中的估值器。
#### SpringSpec弹簧效果
1. 代码

```
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
```
2. 效果
![](https://files.mdnice.com/user/15648/95179cba-7ae4-448c-afe2-acc67ec875bc.gif)

#### TweenSpec动画时间可控
1. 代码

```
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
```
2. 效果
![](https://files.mdnice.com/user/15648/21d01863-c9e6-4e61-9cb0-9ce5fbc3bed9.gif)

#### FrameSpec
1. 代码

```
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

```
2. 效果

![](https://files.mdnice.com/user/15648/8314ac47-a9d2-44a0-9077-d3aecd912f47.gif)


#### RepeatableSpec实现有限次数的重复动画
执行有限次数动画后自动停止
1. 代码

```
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
```
2. 效果

代码中设置了重复5次，所以反复执行五次后动画结束
![](https://files.mdnice.com/user/15648/8b73a96d-68cd-4fdd-aef3-3189febc5f91.gif)


#### InfiniteRepeatableSpec无限次数执行动画
动画会无限次的执行下去，直到视图被移除
1. 代码

```
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
```
2. 效果


![](https://files.mdnice.com/user/15648/a97c0af3-1989-4a16-b69a-06f138927130.gif)


### Easing
Easing类似于我们原生动画中的差值器

有以下几种选择：

- FastOutSlowInEasing
- LinearOutSlowInEasing
- FastOutLinearInEasing
- LinearEasing
- CubicBezierEasing

这几种实现的效果和android原生实现的动画差值器差距很大，甚至看不出有啥效果，所以代码我就不放了。有清楚原因的读者可以联系我

实现效果：
![](https://files.mdnice.com/user/15648/3b3702cf-5551-4c53-9cbf-d99e77fa562c.gif)


### AnimationVector

大多数 Compose 动画 API 都支持将 Float、Color、Dp 以及其他基本数据类型作为开箱即用的动画值，但有时您、我们需要为其他数据类型（包括我们的自定义类型）添加动画效果

本例中实现颜色和大小的变换动画

代码中我们定义了一个AnimSize类，类中的第一个参数是颜色数据，第二个参数是尺寸数据。动画执行过程中会同事改变颜色和控件尺寸效果。
1. 代码

```
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
```
2. 效果

缺点是执行颜色变化过程中有闪烁

![](https://files.mdnice.com/user/15648/fc74ae0c-8572-43cc-a7d0-c0cf04d8a48b.gif)

