package com.example.weatherapp.ui.support

import androidx.annotation.ColorRes
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.weatherapp.R


fun Modifier.withShimmerLoading(
    inProgress: Boolean,
    @ColorRes shimmerColor: Int = R.color.blue_menu
) = composed {
    this.then(
        if (inProgress) {
            Modifier.background(
                getShimmerBrush(
                    color = colorResource(
                        id = shimmerColor
                    )
                )
            )
        } else {
            Modifier
        }
    )
}

@Composable
fun getShimmerBrush(color: Color): Brush {
    val gradient = listOf(
        color.copy(alpha = 1f),
        color.copy(alpha = 0.4f),
        color.copy(alpha = 1f)
    )

    val transition = rememberInfiniteTransition()

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600,
                easing = FastOutLinearInEasing
            )
        )
    )

    return Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
}
