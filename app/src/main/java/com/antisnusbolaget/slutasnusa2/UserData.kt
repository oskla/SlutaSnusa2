package com.antisnusbolaget.slutasnusa2

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(val quitDate: String? = null, val costPerUnit: String?, val unitPerWeek: String?)
