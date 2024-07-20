package com.example.weatherapp.ui.support

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    redesignPrimary: Color,
    redesignSecondary: Color,
    redesignSecondaryPressed: Color,
    redesignDisabled: Color,
    redesignBlack: Color,
    redesignBlackPressed: Color,
    redesignBlack65: Color,
    redesignDashboard: Color,
    redesignGray1: Color,
    redesignGray2: Color,
    redesignGray3: Color,
    redesignGray4: Color,
    redesignGray5: Color,
    redesignHover: Color,
    redesignOverlay: Color,
    redesignBackgroundGray: Color,
    redesignWhite: Color,
    redesignHighlight: Color,
    redesignLoyalty1: Color,
    redesignLoyalty2: Color,
    redesignLoyalty3: Color,
    redesignLoyalty4: Color,
    redesignGreen: Color,
    redesignPromotion: Color,
    redesignSale: Color,
    redesignToastBackground: Color,
    rewardsBackground: Color,
    error: Color,
    blue: Color,
    redesignSystemPurple: Color
) {

    var redesignPrimary by mutableStateOf(redesignPrimary)
        private set
    var redesignSecondary by mutableStateOf(redesignSecondary)
        private set
    var redesignSecondaryPressed by mutableStateOf(redesignSecondaryPressed)
        private set
    var redesignDisabled by mutableStateOf(redesignDisabled)
        private set
    var redesignBlack by mutableStateOf(redesignBlack)
        private set
    var redesignBlackPressed by mutableStateOf(redesignBlackPressed)
        private set
    var redesignBlack65 by mutableStateOf(redesignBlack65)
        private set
    var redesignDashboard by mutableStateOf(redesignDashboard)
        private set
    var redesignGray1 by mutableStateOf(redesignGray1)
        private set
    var redesignGray2 by mutableStateOf(redesignGray2)
        private set
    var redesignGray3 by mutableStateOf(redesignGray3)
        private set
    var redesignGray4 by mutableStateOf(redesignGray4)
        private set
    var redesignGray5 by mutableStateOf(redesignGray5)
        private set
    var redesignHover by mutableStateOf(redesignHover)
        private set
    var redesignOverlay by mutableStateOf(redesignOverlay)
        private set
    var redesignBackgroundGray by mutableStateOf(redesignBackgroundGray)
        private set
    var redesignWhite by mutableStateOf(redesignWhite)
        private set
    var redesignHighlight by mutableStateOf(redesignHighlight)
        private set
    var redesignLoyalty1 by mutableStateOf(redesignLoyalty1)
        private set
    var redesignLoyalty2 by mutableStateOf(redesignLoyalty2)
        private set
    var redesignLoyalty3 by mutableStateOf(redesignLoyalty3)
        private set
    var redesignLoyalty4 by mutableStateOf(redesignLoyalty4)
        private set
    var redesignGreen by mutableStateOf(redesignGreen)
        private set
    var redesignPromotion by mutableStateOf(redesignPromotion)
        private set
    var redesignSale by mutableStateOf(redesignSale)
        private set
    var redesignToastBackground by mutableStateOf(redesignToastBackground)
        private set
    var rewardsBackground by mutableStateOf(rewardsBackground)
        private set

    var error by mutableStateOf(error)
        private set

    var blue by mutableStateOf(blue)
        private set

    var redesignSystemPurple by mutableStateOf(redesignSystemPurple)
        private set

    fun copy(
        redesignPrimary: Color = this.redesignPrimary,
        redesignSecondary: Color = this.redesignSecondary,
        redesignSecondaryPressed: Color = this.redesignSecondaryPressed,
        redesignDisabled: Color = this.redesignDisabled,
        redesignBlack: Color = this.redesignBlack,
        redesignBlackPressed: Color = this.redesignBlackPressed,
        redesignBlack65: Color = this.redesignBlack65,
        redesignDashboard: Color = this.redesignDashboard,
        redesignGray1: Color = this.redesignGray1,
        redesignGray2: Color = this.redesignGray2,
        redesignGray3: Color = this.redesignGray3,
        redesignGray4: Color = this.redesignGray4,
        redesignGray5: Color = this.redesignGray5,
        redesignHover: Color = this.redesignHover,
        redesignOverlay: Color = this.redesignOverlay,
        redesignBackgroundGray: Color = this.redesignBackgroundGray,
        redesignWhite: Color = this.redesignWhite,
        redesignHighlight: Color = this.redesignHighlight,
        redesignLoyalty1: Color = this.redesignLoyalty1,
        redesignLoyalty2: Color = this.redesignLoyalty2,
        redesignLoyalty3: Color = this.redesignLoyalty3,
        redesignLoyalty4: Color = this.redesignLoyalty4,
        redesignGreen: Color = this.redesignGreen,
        redesignPromotion: Color = this.redesignPromotion,
        redesignSale: Color = this.redesignSale,
        redesignToastBackground: Color = this.redesignToastBackground,
        rewardsBackground: Color = this.rewardsBackground,
        error: Color = this.error,
        blue: Color = this.blue,
        redesignSystemPurple: Color = this.redesignSystemPurple
    ) = AppColors(
        redesignPrimary = redesignPrimary,
        redesignSecondary = redesignSecondary,
        redesignSecondaryPressed = redesignSecondaryPressed,
        redesignDisabled = redesignDisabled,
        redesignBlack = redesignBlack,
        redesignBlackPressed = redesignBlackPressed,
        redesignBlack65 = redesignBlack65,
        redesignDashboard = redesignDashboard,
        redesignGray1 = redesignGray1,
        redesignGray2 = redesignGray2,
        redesignGray3 = redesignGray3,
        redesignGray4 = redesignGray4,
        redesignGray5 = redesignGray5,
        redesignHover = redesignHover,
        redesignOverlay = redesignOverlay,
        redesignBackgroundGray = redesignBackgroundGray,
        redesignWhite = redesignWhite,
        redesignHighlight = redesignHighlight,
        redesignLoyalty1 = redesignLoyalty1,
        redesignLoyalty2 = redesignLoyalty2,
        redesignLoyalty3 = redesignLoyalty3,
        redesignLoyalty4 = redesignLoyalty4,
        redesignGreen = redesignGreen,
        redesignPromotion = redesignPromotion,
        redesignSale = redesignSale,
        redesignToastBackground = redesignToastBackground,
        rewardsBackground = rewardsBackground,
        error = error,
        blue = blue,
        redesignSystemPurple = redesignSystemPurple
    )

    fun updateColorsFrom(other: AppColors) {
        redesignPrimary = other.redesignPrimary
        redesignSecondary = other.redesignSecondary
        redesignSecondaryPressed = other.redesignSecondaryPressed
        redesignDisabled = other.redesignDisabled
        redesignBlack = other.redesignBlack
        redesignDashboard = other.redesignDashboard
        redesignGray1 = other.redesignGray1
        redesignGray2 = other.redesignGray2
        redesignGray3 = other.redesignGray3
        redesignGray4 = other.redesignGray4
        redesignGray5 = other.redesignGray5
        redesignHover = other.redesignHover
        redesignOverlay = other.redesignOverlay
        redesignBackgroundGray = other.redesignBackgroundGray
        redesignWhite = other.redesignWhite
        redesignHighlight = other.redesignHighlight
        redesignLoyalty1 = other.redesignLoyalty1
        redesignLoyalty2 = other.redesignLoyalty2
        redesignLoyalty3 = other.redesignLoyalty3
        redesignLoyalty4 = other.redesignLoyalty4
        redesignGreen = other.redesignGreen
        redesignPromotion = other.redesignPromotion
        redesignSale = other.redesignSale
        redesignToastBackground = other.redesignToastBackground
        rewardsBackground = other.rewardsBackground
        blue = other.blue
    }
}

// PLEASE DO NOT ADD NEW COLORS HERE, CREATE LOCAL COLOR RESOURCE IN YOUR MODULE IF NEEDED

fun redesignColors() = AppColors(
    redesignPrimary = Color(0xFFE01A2B),
    redesignSecondary = Color(0xFFAB0000),
    redesignSecondaryPressed = Color(0xbfab0000),
    redesignDisabled = Color(0xFFEEA1A1),
    redesignBlack = Color(0xFF000000),
    redesignBlackPressed = Color(0xFF404040),
    redesignBlack65 = Color(0xFF595959),
    redesignDashboard = Color(0xFF2C2C37),
    redesignGray1 = Color(0xFF626369),
    redesignGray2 = Color(0xFF959499),
    redesignGray3 = Color(0xFFC0C0C0),
    redesignGray4 = Color(0xFFD6D6D6),
    redesignGray5 = Color(0xFFE6E6E6),
    redesignHover = Color(0xFFF1F1F1),
    redesignOverlay = Color(0x80000000),
    redesignBackgroundGray = Color(0xFFF8F8F8),
    redesignWhite = Color(0xFFFFFFFF),
    redesignHighlight = Color(0xFFD90EAC),
    redesignLoyalty1 = Color(0xFF924B2C),
    redesignLoyalty2 = Color(0xFFB19050),
    redesignLoyalty3 = Color(0xFF667479),
    redesignLoyalty4 = Color(0xFF959499),
    redesignGreen = Color(0xFF008757),
    redesignPromotion = Color(0xFFFFF402),
    redesignSale = Color(0xFFE01A2B),
    redesignToastBackground = Color(0xD9000000),
    rewardsBackground = Color(0xFFF7F5F1),
    error = Color(0xFFE01A2B),
    blue = Color(0xFF455D90),
    redesignSystemPurple = Color(0xFF6750A4)
)
