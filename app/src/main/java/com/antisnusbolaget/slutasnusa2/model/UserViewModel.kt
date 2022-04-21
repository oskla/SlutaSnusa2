package com.antisnusbolaget.slutasnusa2.model

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.antisnusbolaget.slutasnusa2.UserData
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.database.DatabaseReference
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class UserViewModel(application: Application) : AndroidViewModel(application) {

    // CalenderBuilder
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Start date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

    // DateFormatter & Empty variable for user pick
    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN)
    var quitDate = ""
    var storageIsFull = false

    //___________________________________________________________________________________________

    // LiveData variables
    private val _unitPerWeek = MutableLiveData<Int>(0)
    val unitPerWeek: LiveData<Int> = _unitPerWeek
    private val _costPerUnit = MutableLiveData<Int>(0)
    val costPerUnit: LiveData<Int> = _costPerUnit
    private val _daysWithout = MutableLiveData<Int>(0)
    val daysWithout: LiveData<Int> = _daysWithout
    private val _totalMoneySaved = MutableLiveData<Int>(0)
    val totalMoneySaved: LiveData<Int> = _totalMoneySaved
    //___________________________________________________________________________________________

    // Setters for LiveData variables
    private fun setUnitQuantity(unitPerWeek: Int){ _unitPerWeek.value = unitPerWeek }
    private fun setCostPerUnit(costPerUnit: Int) { _costPerUnit.value = costPerUnit }
    private fun setDaysWithout(daysWithout: Int) { _daysWithout.value = daysWithout }
    private fun setTotalMoneySaved(totalMoneySaved: Int) { _totalMoneySaved.value = totalMoneySaved }
    //____________________________________________________________________________________________

    // Functions
    fun calenderSelection(manager: FragmentManager) { // If user press NO = calender opens
        datePicker.show(manager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            val date = dateFormatter.format(Date(it))
            quitDate = date
            saveLocal("date", quitDate)
            dateSinceQuit()
        }
    }

    fun noCalenderSelection(){ // If user press YES = calender wont open, quitDate = today
        val noSelection = dateFormatter.format(Date())
        quitDate = noSelection
        saveLocal("date",quitDate)
        dateSinceQuit()
    }

    private fun dateSinceQuit(){ //Calculating the diff in time from two dates
        val currentDate = dateFormatter.format(Date())
        val date1: Date = dateFormatter.parse(currentDate) as Date
        val date2: Date = dateFormatter.parse(quitDate) as Date
        val diffBetween: Long = Math.abs(date1.time - date2.time)
        val diff: Long = TimeUnit.DAYS.convert(diffBetween, TimeUnit.MILLISECONDS)
        setDaysWithout(diff.toInt())
    }

    fun moneySaved() { //Calculating the total money saved based on how many units, cost etc
        val costPerUnit = costPerUnit.value //Local value
        val unitPerWeek = unitPerWeek.value // -||-
        val daysWithout = daysWithout.value // -||-
        val unitsCost = unitPerWeek?.times(costPerUnit!!)
        val costPerDay = unitsCost?.div(7)
        val moneySaved = costPerDay?.times(daysWithout!!)
        if (moneySaved != null) {
            setTotalMoneySaved(moneySaved)
        }
    }

    fun dbWrite(myDb: DatabaseReference) { //Firebase -Database
        val userData = UserData(quitDate,costPerUnit.value.toString(), unitPerWeek.value.toString())
         myDb.child("User1")
            .push()
            .setValue(userData)
            .addOnSuccessListener { println("DB success") }
            .addOnFailureListener{ println("DB bad") }
    }

    fun saveLocal(key: String, value: String){ //saving data locally
        val context = getApplication<Application>().applicationContext
        val file = key
        val data: String = value
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())

        } catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }
        Toast.makeText(context,"data save", Toast.LENGTH_SHORT).show()
    }

    fun readLocal(key: String){ // reading saved data
        val context = getApplication<Application>().applicationContext
        val filename = key
        if(filename.trim()!=""){
            var fileInputStream: FileInputStream? = null
            fileInputStream = context.openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
                when (filename){
                    "unit" -> setUnitQuantity(stringBuilder.toString().toInt())
                    "cost" -> setCostPerUnit(stringBuilder.toString().toInt())
                    "date" -> quitDate = stringBuilder.toString()
                }
            }
        }

    }
}


