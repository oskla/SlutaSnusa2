package com.antisnusbolaget.slutasnusa2.model

<<<<<<< Updated upstream
import android.app.PendingIntent.getActivity
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.MainActivity
=======
import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
>>>>>>> Stashed changes
import com.antisnusbolaget.slutasnusa2.UserData
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


<<<<<<< Updated upstream
class UserViewModel : ViewModel() {

=======
class UserViewModel(application: Application) : AndroidViewModel(application) {
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream

    // Setters for variables
    fun setUnitQuantity(unitPerWeek: Int){
        _unitPerWeek.value = unitPerWeek
    }
    fun setCostPerUnit(costPerUnit: Int) {
        _costPerUnit.value = costPerUnit
    }
=======
    // Setters for LiveData variables
    fun setUnitQuantity(unitPerWeek: Int) {
        _unitPerWeek.value = unitPerWeek
    }

    fun setCostPerUnit(costPerUnit: Int) {
        _costPerUnit.value = costPerUnit
    }

>>>>>>> Stashed changes
    fun setDaysWithout(daysWithout: Int) {
        _daysWithout.value = daysWithout
    }

    fun setTotalMoneySaved(totalMoneySaved: Int) {
        _totalMoneySaved.value = totalMoneySaved
    }
<<<<<<< Updated upstream
=======
    //____________________________________________________________________________________________
>>>>>>> Stashed changes

    // Functions
    fun calenderSelection(manager: FragmentManager) {
        datePicker.show(manager, "tag")

        datePicker.addOnPositiveButtonClickListener {
            val date = dateFormatter.format(Date(it))
            quitDate = date
<<<<<<< Updated upstream
=======
            //saveLocal("date", quitDate)
>>>>>>> Stashed changes
            dateSinceQuit()
        }
    }

<<<<<<< Updated upstream
    //TODO Denna funktion körs om man trycker på "NEJ" i datefragment.
    //TODO noSelection blir dagens datum, userDate som används i dateSinceQuit() blir till dagens datum.

    fun noCalenderSelection(){
        val noSelection = dateFormatter.format(Date())
        quitDate = noSelection
        dateSinceQuit()
    }

    fun dateSinceQuit(){
=======
    fun noCalenderSelection() { // If user press YES = calender wont open, quitDate = today
        val noSelection = dateFormatter.format(Date())
        quitDate = noSelection
        //saveLocal("date",quitDate)
        dateSinceQuit()
    }

    fun dateSinceQuit() { //Calculating the diff in time from two dates
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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
=======
        // dateSinceQuit()
    }

    fun dbWrite(myDb: DatabaseReference) { //Firebase -Database
        val userData =
            UserData(quitDate, costPerUnit.value.toString(), unitPerWeek.value.toString())
        myDb.child("User1")
            .push()
            .setValue(userData)
            .addOnSuccessListener { println("DB success") }
            .addOnFailureListener { println("DB bad") }
    }

    fun saveLocal(key: String, value: String) { //saving data locally
        val context = getApplication<Application>().applicationContext
        val file = key
        val data: String = value
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
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
        Toast.makeText(context, "data save", Toast.LENGTH_SHORT).show()
    }

    fun readLocal(key: String) {
        // reading saved data
        val context = getApplication<Application>().applicationContext
        val filename = key
        if (filename.trim() != "") {
                var fileInputStream: FileInputStream? = null
           var dir = context.fileList()
            if (dir.isEmpty()) {
                false
            } else {
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



        }

>>>>>>> Stashed changes


