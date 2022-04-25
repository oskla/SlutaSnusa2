package com.antisnusbolaget.slutasnusa2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentUnitBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import java.util.*


class UnitFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentUnitBinding? = null
    private var tempUnit: Int = 0

    // Prevents multiple navController calls
    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onCreate(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            super.onCreate(savedInstanceState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentUnitBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            twUnits.text = sharedViewModel.unitPerWeek.value.toString()

            btnMinus.setOnClickListener {
                if(tempUnit > 0){
                    tempUnit -= 1
                }
                else{
                    println("smting WRONG")
                }

                twUnits.text = tempUnit.toString()
            }

            btnPlus.setOnClickListener {
                tempUnit += 1
                twUnits.text = tempUnit.toString()
                //openLocalCal()
                writeToCal()
            }

            lifecycleScope.launchWhenResumed {
                btnGoToCost.setOnClickListener {

                    // Set UnitQuantity liveData
                    sharedViewModel.setUnitQuantity(tempUnit)
                    findNavController().safelyNavigate(R.id.action_unitFragment_to_costFragment)
                }
            }
        }
    }
        fun openLocalCal(){
            val cal: Calendar = GregorianCalendar()
            cal.setTime(Date())
            cal.add(Calendar.MONTH, 2)
            val time: Long = cal.getTime().getTime()
            val builder: Uri.Builder = CalendarContract.CONTENT_URI.buildUpon()
            builder.appendPath("time")
            builder.appendPath(java.lang.Long.toString(time))
            val intent = Intent(Intent.ACTION_VIEW, builder.build())
            startActivity(intent)
        }

    fun writeToCal(){
        val cal: Calendar = GregorianCalendar()
        cal.time = Date()
        cal.add(Calendar.MONTH, 2)
        val intent = Intent(Intent.ACTION_INSERT)
        intent.data = Events.CONTENT_URI
        intent.putExtra(Events.TITLE, "Some Test Event")
        intent.putExtra(Events.ALL_DAY, true)
        intent.putExtra(
            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
            cal.time.time
        )
        intent.putExtra(
            CalendarContract.EXTRA_EVENT_END_TIME,
            cal.time.time + 600000
        )
        intent.putExtra(
            Intent.EXTRA_EMAIL,
            "johan@gmail.com, oskar@gmail.com"
        )
        startActivity(intent)
    }
}




