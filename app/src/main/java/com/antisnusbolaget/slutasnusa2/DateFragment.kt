package com.antisnusbolaget.slutasnusa2

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentDateBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*


class DateFragment : Fragment() {

    //PERMISSIONS
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                println("RequestLauncher Granted")
            } else {
                println("RequestLauncher Denied")
            }
        }
    private lateinit var layout: View
//____________________________________________________________

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentDateBinding? = null

    // Prevents multiple navController calls
    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this NICE fragment
        val fragmentBinding = FragmentDateBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        val view = binding!!.root
        layout = view.rootView


        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            twNo.setOnClickListener {
                val manager = childFragmentManager
                if (sharedViewModel.datePicker.isAdded) { null }else { sharedViewModel.calenderSelection(manager)} // Prevents multiple functions-calls / app crash


                sharedViewModel.datePicker.addOnPositiveButtonClickListener {
                    // IF-condition to check if the date is ahead in time
                    if(sharedViewModel.dateFormatter.parse(sharedViewModel.quitDate)!! > sharedViewModel.dateFormatter.parse(sharedViewModel.currentDate))
                    { println("You cant quit ahead in time")
                    }else{
                        // IF-condition to check if permission is granted or not.
                        if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_CALENDAR) == PERMISSION_GRANTED){
                            writeToCal()
                        }
                            lifecycleScope.launchWhenResumed { // Prevents multiple navController calls
                            findNavController().safelyNavigate(R.id.action_dateFragment_to_homeFragment)
                        }
                    }
                }
            }

            twYes.setOnClickListener {
                sharedViewModel.noCalenderSelection()
                lifecycleScope.launchWhenResumed { // Prevents multiple navController calls
                    findNavController().safelyNavigate(R.id.action_dateFragment_to_homeFragment)
                }
            }
            btnPermission.setOnClickListener{
                onClickRequestPermission()
            }
        }
    }

    fun onClickRequestPermission() {

        when (PackageManager.PERMISSION_GRANTED ) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.WRITE_CALENDAR
            ) -> {
                println("Permission already granted")
            }
            else -> {
                requestPermissionLauncher.launch(android.Manifest.permission.WRITE_CALENDAR)
                println("Do you wish to grant permission?")
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
        intent.data = CalendarContract.Events.CONTENT_URI
        intent.putExtra(CalendarContract.Events.TITLE, "Idag slutade jag snusa!!")
        intent.putExtra(CalendarContract.Events.ALL_DAY, true)
        intent.putExtra(
            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
            cal.time.time
        )
        startActivity(intent)
    }
}

