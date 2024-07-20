package com.example.weatherapp.ui.support

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


const val SHIMMER_DURATION = 500L
const val SHIMMER_BASE_ALPHA = 1f
const val SHIMMER_REPEAT_DELAY = 1000L
const val SHIMMER_HIGHLIGHT_ALPHA = 0.15f
const val SHIMMER_DROP_OFF = 1f

fun ImageView.getShimmerDrawable(): ShimmerDrawable {
    return getShimmerDrawable(context)
}

fun getShimmerDrawable(context: Context): ShimmerDrawable {
    return ShimmerDrawable().apply {
        setShimmer(
            Shimmer.ColorHighlightBuilder()
                .setDuration(SHIMMER_DURATION)
                .setBaseAlpha(SHIMMER_BASE_ALPHA)
                .setRepeatDelay(SHIMMER_REPEAT_DELAY)
                .setHighlightAlpha(SHIMMER_HIGHLIGHT_ALPHA)
                .setBaseColor(
                    ContextCompat.getColor(
                        context,
                        R.color.gray
                    )
                )
                .setHighlightColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
                .setDropoff(SHIMMER_DROP_OFF)
                .build()
        )
    }
}
