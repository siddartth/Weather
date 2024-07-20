package com.example.weatherapp.ui.support

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import coil.compose.rememberImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult


/**
 * @see adp.da.imageloader.databinding.setImageUrl
 */
@Composable
fun rememberImagePainter(
    imageUrl: String?,
    placeHolderImage: Drawable? = null,
    errorImage: Drawable? = null,
    emptyUrlImage: Drawable? = null,
    useShimmerPlaceholder: Boolean? = false,
    onCancel: (() -> Unit)? = null,
    onError: (() -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onSuccess: (() -> Unit)? = null
): Painter {
    val context = LocalContext.current
    if (imageUrl.isNullOrEmpty()) {
        val normalizedEmptyImageUrl = emptyUrlImage
            ?: ContextCompat.getDrawable(context, R.drawable.ic_loading_image)
        return rememberImagePainter(
            normalizedEmptyImageUrl
        )
    }

    val normalizedPlaceHolder = if (useShimmerPlaceholder == true) {
        getShimmerDrawable(context)
    } else {
        placeHolderImage
            ?: ContextCompat.getDrawable(context, R.drawable.ic_loading_image)
    }
    val normalizedErrorImage = errorImage
        ?: ContextCompat.getDrawable(context, R.drawable.ic_error_image)

    return rememberImagePainter(
        data = imageUrl,
        builder = {
            placeholder(normalizedPlaceHolder)
            error(normalizedErrorImage)
            listener(object : ImageRequest.Listener {
                override fun onCancel(request: ImageRequest) {
                    onCancel?.invoke()
                }

                override fun onError(request: ImageRequest, result: ErrorResult) {
                    onError?.invoke()
                }

                override fun onStart(request: ImageRequest) {
                    onStart?.invoke()
                }

                override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                    onSuccess?.invoke()
                }
            })
        }
    )
}
