package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentDateBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.time.Duration.Companion.days


class DateFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentDateBinding? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentDateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Start date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()


        binding?.apply {



            btnTest.setOnClickListener {
                datePicker.show(childFragmentManager,"tag")
                datePicker.addOnPositiveButtonClickListener {
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                    val date = dateFormatter.format(Date(it))
                    Toast.makeText(context, "$date is selected", Toast.LENGTH_LONG).show()

                    println(date)
                    sharedViewModel.setDate(date)
                    findNavController().navigate(R.id.action_dateFragment_to_homeFragment)

                }

            }

        }
    }

}