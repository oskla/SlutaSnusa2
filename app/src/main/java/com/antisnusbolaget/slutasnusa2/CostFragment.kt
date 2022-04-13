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
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.slider.Slider
import java.text.NumberFormat
import java.util.*


class CostFragment : Fragment() {


    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentCostBinding? = null
    private var sliderValue: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCostBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {

          /*  sliderCost.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                    println("Start")
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    println("stop")



                }

            }) */


            sliderCost.addOnChangeListener { slider, value, fromUser ->
                sliderCost.setLabelFormatter {
                val format = NumberFormat.getCurrencyInstance()
                format.maximumFractionDigits = 0
                format.currency = Currency.getInstance("SEK")
                format.format(value.toInt())


            }

                println(value.toInt())
                sliderValue = value.toInt()
                twCostPerUnit.text = ("${sliderValue} kr")

            }


            btnGoToNext.setOnClickListener{
                lifecycleScope.launchWhenResumed {

                    sharedViewModel.setCostPerUnit(sliderValue)

                    println(sliderValue)
                    findNavController().safelyNavigate(R.id.action_costFragment_to_dateFragment)

                }
            }


        }
    }
}

