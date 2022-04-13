package com.antisnusbolaget.slutasnusa2.model

import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext


class UserViewModel : ViewModel() {

    // CalenderBuilder
    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Start date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

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


    fun calenderSelection(manager: FragmentManager) {
        datePicker.show(manager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val date = dateFormatter.format(Date(it))

            println(date)
            setDate(date)
        }
    }



}