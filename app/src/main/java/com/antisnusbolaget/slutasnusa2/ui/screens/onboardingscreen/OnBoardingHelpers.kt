package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import java.util.concurrent.TimeUnit

class OnBoardingHelpers() {
    private val currentDate = System.currentTimeMillis()

    fun daysSinceQuit(selectedDate: Long): Long = TimeUnit.MILLISECONDS.toDays(currentDate - selectedDate)
}
