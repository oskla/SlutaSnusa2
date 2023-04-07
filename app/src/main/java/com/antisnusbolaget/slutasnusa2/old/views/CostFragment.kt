package com.antisnusbolaget.slutasnusa2.old.views

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
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.databinding.FragmentCostBinding
import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel
import java.text.NumberFormat
import java.util.*

class CostFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentCostBinding? = null
    private var sliderValue: Int = 1

    // Prevents multiple navController calls
    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCostBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            twCostPerUnit.text = "$sliderValue kr"

            sliderCost.addOnChangeListener { _, value, _ ->
                sliderCost.setLabelFormatter {
                val format = NumberFormat.getCurrencyInstance()
                format.maximumFractionDigits = 0
                format.currency = Currency.getInstance("SEK")
                format.format(value.toInt())
                }
                sliderValue = value.toInt()
                twCostPerUnit.text = "$sliderValue kr"
            }

            btnGoToCost.setOnClickListener{
                lifecycleScope.launchWhenResumed { // Prevents multiple navController calls

                    // Set Cost liveData
                    sharedViewModel.setCostPerUnit(sliderValue)
                    findNavController().safelyNavigate(R.id.action_costFragment_to_dateFragment)
                }
            }
        }
    }
}

