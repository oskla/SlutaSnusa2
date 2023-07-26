package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import java.util.concurrent.TimeUnit

// TODO will probably have some sort of DateHandler/DateProvider in the future which will handle all logic connected to Dates.
class OnBoardingHelpers() {
    private val currentDate = System.currentTimeMillis()

    fun daysSinceQuit(selectedDate: Long): Long = TimeUnit.MILLISECONDS.toDays(currentDate - selectedDate)
}
