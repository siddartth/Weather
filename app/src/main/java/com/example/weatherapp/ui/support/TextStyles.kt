package com.example.weatherapp.ui.support

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 *  These are support styles for compose view.
 *
 *  There sets of global styling and components usually created for use
 *
 *  These are few styling that i thought would be used for this weather app
 */
data class TextStyles(
    private val colors: AppColors = redesignColors(),
    val bodyNormal: TextStyle = TextStyle(
        color = colors.redesignBlack,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400
    ),
    val redesignBodyMedium: TextStyle =
        bodyNormal.copy(
            fontWeight = FontWeight.W500
        ),
    val redesignBodyLarge: TextStyle =
        bodyNormal.copy(
            fontSize = 16.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.W500
        ),
    val redesignBodySmall: TextStyle =
        bodyNormal.copy(
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),

    val redesignH1: TextStyle =
        bodyNormal.copy(
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.015).em,
            fontWeight = FontWeight.W700
        ),

    val redesignH2: TextStyle =
        redesignH1.copy(
            fontSize = 28.sp,
            lineHeight = 35.sp
        ),
    val redesignH4: TextStyle =
        bodyNormal.copy(
            fontSize = 86.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.015).em,
            fontWeight = FontWeight.W700
        )
)