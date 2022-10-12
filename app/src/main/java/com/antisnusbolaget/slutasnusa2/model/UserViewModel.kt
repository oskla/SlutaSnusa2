package com.antisnusbolaget.slutasnusa2.model

import android.animation.ValueAnimator
import android.app.Application
import android.content.Context
import android.util.TypedValue
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antisnusbolaget.slutasnusa2.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class UserViewModel(application: Application) : AndroidViewModel(application) {

    // CalenderBuilder
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Start date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .setTheme(R.style.ThemeOverlay)
        .build()


    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN) // DateFormatter & Empty variable for user pick
    var quitDate = "01-05-2022"
    val currentDate: String = dateFormatter.format(Date())
    var daysLeftAchievement = 0
    var animationRuns = 1 // counter for animation in HomeFragment
    var settingsChanged = false
    //___________________________________________________________________________________________

    // LiveData variables
    private val _unitPerWeek = MutableLiveData(0)
    val unitPerWeek: LiveData<Int> = _unitPerWeek
    private val _costPerUnit = MutableLiveData(0)
    val costPerUnit: LiveData<Int> = _costPerUnit
    private val _daysWithout = MutableLiveData(0)
    val daysWithout: LiveData<Int> = _daysWithout
    private val _totalMoneySaved = MutableLiveData(0)
    val totalMoneySaved: LiveData<Int> = _totalMoneySaved
    //___________________________________________________________________________________________

    // Setters for LiveData variables
        fun setUnitQuantity(unitPerWeek: Int) {
            _unitPerWeek.value = unitPerWeek
        }
        fun setCostPerUnit(costPerUnit: Int) {
            _costPerUnit.value = costPerUnit
        }
        private fun setDaysWithout(daysWithout: Int) {
            _daysWithout.value = daysWithout
        }
        private fun setTotalMoneySaved(totalMoneySaved: Int) {
            _totalMoneySaved.value = totalMoneySaved
        }
    //____________________________________________________________________________________________

    // Functions
    fun calenderSelection(manager: FragmentManager) { // If user press NO = calender opens
        datePicker.show(manager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            val date = dateFormatter.format(Date(it))
            quitDate = date
            dateSinceQuit()
        }
    }

    fun noCalenderSelection() { // If user press YES = calender wont open, quitDate = today
        val noSelection = dateFormatter.format(Date())
        quitDate = noSelection
        dateSinceQuit()
    }

    fun dateSinceQuit() { //Calculating the diff in time from two dates
        val date1: Date = currentDate.let { dateFormatter.parse(it) } as Date
        val date2: Date = dateFormatter.parse(quitDate) as Date
        val diffBetween: Long = abs(date1.time - date2.time)
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

    fun daysLeftAchievement(achievementDays: Int): Int { // Calculating days until achievement is fulfilled
        val daysLeftAchievement2 = (achievementDays - daysWithout.value!!)
        return if (daysLeftAchievement2 > 0) {
            daysLeftAchievement2
        } else {
            0
        }
    }

    fun moneySavedAchievement( // Calculating amount of money saved upon reaching achievement
        achievementDays: Int,
        textView: TextView,
    ): Int {
        val costPerWeek = costPerUnit.value?.times(unitPerWeek.value!!)
        val costPerDay = costPerWeek?.div(7)
        val moneySavedAchievement2 = costPerDay?.times(achievementDays)!!

        if (moneySavedAchievement2 > 9999) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 75F)
        }
        println(moneySavedAchievement2)

        return if (moneySavedAchievement2 > 0) {
            moneySavedAchievement2
        } else {
            0
        }
    }

    fun saveLocal(key: String, value: String) { //saving data locally
        val context = getApplication<Application>().applicationContext
        val data: String = value
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(key, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readLocal(key: String) { // reading saved data
        val context = getApplication<Application>().applicationContext
        if (key.trim() != "") {
            val fileInputStream: FileInputStream?
            val dir = context.fileList()
            if (dir.isEmpty()) { // If fileList=empty - don't run function
                return
            } else {

                val file = context.getFileStreamPath(key)
                if(file == null || !file.exists()) {
                    return
                }
                fileInputStream = context.openFileInput(key)

                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String?
                while (run {
                        text = bufferedReader.readLine()
                        text
                    } != null) {
                    stringBuilder.append(text)
                    when (key) { // Looping through list of keys to set data
                        "unit" -> setUnitQuantity(stringBuilder.toString().toInt())
                        "cost" -> setCostPerUnit(stringBuilder.toString().toInt())
                        "date" -> quitDate = stringBuilder.toString()
                    }
                }
            }
        }
    }

    fun totalValuesAnimator(textView: TextView, value: Int) { // Animates numbers on homeScreen
        val animator = ValueAnimator.ofInt(0, value)
        animator.duration = 1500 //
        animator.addUpdateListener { animation ->
            textView.text = animation.animatedValue.toString()
        }
        animator.start()
    }

    fun totalValuesAnimatorSettings(textView: TextView, value: Int) { // Animates numbers on homeScreen
        val animator = ValueAnimator.ofInt(0, value)
        animator.duration = 500 //
        animator.addUpdateListener { animation ->
            textView.text = animation.animatedValue.toString()
        }
        animator.start()
    }
}




