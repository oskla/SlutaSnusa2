package com.antisnusbolaget.slutasnusa2

import com.antisnusbolaget.slutasnusa2.di.viewModel
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModules : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        viewModel.verify()
    }
}
