package com.example.weatherapp.ui.support

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment


/**
 * Creates compose view and sets the Jetpack Compose UI content for it.
 * Disposes the composition when the view's [LifecycleOwner][androidx.lifecycle.LifecycleOwner]
 * is destroyed.
 * Uses MdcTheme adapter to read colors, typography and shape from the View-based theme.
 * @see [ComposeView.setContent]
 * @see [ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed]
 * @see [MdcTheme]
 */
fun Fragment.createComposeView(
    content: @Composable () -> Unit
): ComposeView {
    val composeView = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            content()
        }
    }
    return composeView
}