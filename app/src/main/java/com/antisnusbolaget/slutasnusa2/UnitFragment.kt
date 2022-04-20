package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentCostBinding
import com.antisnusbolaget.slutasnusa2.databinding.FragmentUnitBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.slider.Slider
import java.text.NumberFormat
import java.util.*

class UnitFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentUnitBinding? = null
    var tempUnit: Int = 0

    // Prevents multiple navController calls
    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onCreate(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            super.onCreate(savedInstanceState)
            println("Saved instance state: $savedInstanceState")
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

        println(sharedViewModel.getUnit.value)

        binding?.apply {
            twUnits.text = sharedViewModel.unitPerWeek.value.toString()

            btnMinus.setOnClickListener {
                tempUnit -= 1
                twUnits.text = tempUnit.toString()
            }

            btnPlus.setOnClickListener {
                tempUnit += 1
                twUnits.text = tempUnit.toString()
            }

            lifecycleScope.launchWhenResumed {
                btnGoToCost.setOnClickListener {
                    sharedViewModel.setUnitQuantity(tempUnit)
                    sharedViewModel.setterUnitPWeek()
                    findNavController().safelyNavigate(R.id.action_unitFragment_to_costFragment)
                }

            }
        }

    }
}



