package com.antisnusbolaget.slutasnusa2.model

import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.Period
import java.util.*
import kotlin.coroutines.coroutineContext


class UserViewModel : ViewModel() {

    // CalenderBuilder
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Start date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

    // Variables
    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN)

    private var userDate = ""

    private val _unitPerWeek = MutableLiveData<Int>(10)
    val unitPerWeek: LiveData<Int> = _unitPerWeek

    private val _costPerUnit = MutableLiveData<Int>(0)
    var costPerUnit: LiveData<Int> = _costPerUnit

    private val _quitDate = MutableLiveData<Any>("Today")
    val quitDate: LiveData<Any> = _quitDate


    // Setters for variables
    fun setUnitQuantity(unitPerWeek: Int){
        _unitPerWeek.value = unitPerWeek
    }
    fun setCostPerUnit(costPerUnit: Int) {
        _costPerUnit.value = costPerUnit
    }
    fun setDate(quitDate: Any) {
        _quitDate.value = quitDate
    }

    // Functions
    fun calenderSelection(manager: FragmentManager) {
        datePicker.show(manager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            val date = dateFormatter.format(Date(it))
            println(date)
            userDate = date
        }
        dateSinceQuit()
    }

    fun dateSinceQuit(){
        val currentDate = dateFormatter.format(Date())
        val date1: Date = dateFormatter.parse(currentDate) as Date
        val date2: Date = dateFormatter.parse(userDate) as Date

        val dateDiff = date2.compareTo(date1)

        println(dateDiff)


    }
}