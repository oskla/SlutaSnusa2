package com.antisnusbolaget.slutasnusa2.model

import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


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

    private val _daysWithout = MutableLiveData<String>("10")
    val daysWithout: LiveData<String> = _daysWithout


    // Setters for variables
    fun setUnitQuantity(unitPerWeek: Int){
        _unitPerWeek.value = unitPerWeek
    }
    fun setCostPerUnit(costPerUnit: Int) {
        _costPerUnit.value = costPerUnit
    }
    fun setDaysWithout(daysWithout: String) {
        _daysWithout.value = daysWithout
    }

    // Functions
    fun calenderSelection(manager: FragmentManager) {
        datePicker.show(manager, "tag")


        datePicker.addOnPositiveButtonClickListener {
            val date = dateFormatter.format(Date(it))
            userDate = date
            dateSinceQuit()
        }

    }

    fun dateSinceQuit(){
        val currentDate = dateFormatter.format(Date())
        val date1: Date = dateFormatter.parse(currentDate) as Date
        val date2: Date = dateFormatter.parse(userDate) as Date


            val diffInMillies: Long = Math.abs(date1.time - date2.time)
            val diff: Long = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)

            println("the difference is: $diff")
            setDaysWithout(diff.toString())
        

        }




    }
