package com.antisnusbolaget.slutasnusa2.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.util.*




class UserViewModel : ViewModel() {

    private val _unitPerWeek = MutableLiveData<Int>(10)
    val unitPerWeek: LiveData<Int> = _unitPerWeek

    private val _costPerUnit = MutableLiveData<Int>(0)
    var costPerUnit: LiveData<Int> = _costPerUnit

    private val _quitDate = MutableLiveData<String>("Today")
    val quitDate: LiveData<String> = _quitDate



    fun setUnitQuantity(unitPerWeek: Int){
        _unitPerWeek.value = unitPerWeek
    }
    fun setCostPerUnit(costPerUnit: Int) {
        _costPerUnit.value = costPerUnit
    }
    fun setDate(quitDate: String) {
        _quitDate.value = quitDate
    }


}