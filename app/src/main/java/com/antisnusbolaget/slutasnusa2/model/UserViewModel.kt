package com.antisnusbolaget.slutasnusa2.model

import android.app.PendingIntent.getActivity
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.MainActivity
import com.antisnusbolaget.slutasnusa2.UserData
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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

    private var quitDate = ""

    private val _unitPerWeek = MutableLiveData<Int>(0)
    val unitPerWeek: LiveData<Int> = _unitPerWeek

    private val _costPerUnit = MutableLiveData<Int>(0)
    private var costPerUnit: LiveData<Int> = _costPerUnit

    private val _daysWithout = MutableLiveData<Int>(0)
    val daysWithout: LiveData<Int> = _daysWithout

    private val _totalMoneySaved = MutableLiveData<Int>(0)
    val totalMoneySaved: LiveData<Int> = _totalMoneySaved


    // Setters for variables
    fun setUnitQuantity(unitPerWeek: Int){
        _unitPerWeek.value = unitPerWeek
    }
    fun setCostPerUnit(costPerUnit: Int) {
        _costPerUnit.value = costPerUnit
    }
    fun setDaysWithout(daysWithout: Int) {
        _daysWithout.value = daysWithout
    }

    fun setTotalMoneySaved(totalMoneySaved: Int) {
        _totalMoneySaved.value = totalMoneySaved
    }

    // Functions
    fun calenderSelection(manager: FragmentManager) {
        datePicker.show(manager, "tag")

        datePicker.addOnPositiveButtonClickListener {
            val date = dateFormatter.format(Date(it))
            quitDate = date
            dateSinceQuit()
        }
    }

    //TODO Denna funktion körs om man trycker på "NEJ" i datefragment.
    //TODO noSelection blir dagens datum, userDate som används i dateSinceQuit() blir till dagens datum.

    fun noCalenderSelection(){
        val noSelection = dateFormatter.format(Date())
        quitDate = noSelection
        dateSinceQuit()
    }

    fun dateSinceQuit(){
        val currentDate = dateFormatter.format(Date())
        val date1: Date = dateFormatter.parse(currentDate) as Date
        val date2: Date = dateFormatter.parse(quitDate) as Date
            val diffInMillies: Long = Math.abs(date1.time - date2.time)
            val diff: Long = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)

            setDaysWithout(diff.toInt())

            moneySaved()
        }

    fun moneySaved() {

        // Local variables
        val costPerUnit = costPerUnit.value
        val unitPerWeek = unitPerWeek.value
        val daysWithout = daysWithout.value

        // Algorithm
        val unitsCost = unitPerWeek?.times(costPerUnit!!)
        val costPerDay = unitsCost?.div(7)
        val moneySaved = costPerDay?.times(daysWithout!!)

        if (moneySaved != null) {
            setTotalMoneySaved(moneySaved)
        }
    }

 fun dbWrite(myDb: DatabaseReference) {

     val userData = UserData(quitDate,costPerUnit.value.toString(), unitPerWeek.value.toString())

     myDb.child("User1")
         .push()
         .setValue(userData)
         .addOnSuccessListener {
             println("success")
         }.addOnFailureListener{
             println("bad")
         }


    }

    }


