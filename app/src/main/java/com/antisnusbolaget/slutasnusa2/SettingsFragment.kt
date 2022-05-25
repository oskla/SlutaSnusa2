package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentHomeBinding
import com.antisnusbolaget.slutasnusa2.databinding.FragmentSettingsBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentSettingsBinding? = null

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
        val fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentBinding



        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        navBar?.isVisible=false

        binding?.apply {

            inputUnits.hint = sharedViewModel.unitPerWeek.value.toString()
            inputCost.hint = sharedViewModel.costPerUnit.value.toString()
            inputDate.text = sharedViewModel.quitDate

            inputDate.setOnClickListener{
            val manager = childFragmentManager
            if (sharedViewModel.datePicker.isAdded) { null }else { sharedViewModel.calenderSelection(manager)} // Prevents multiple functions-calls / app crash
            //ClickListener on date-popup
            sharedViewModel.datePicker.addOnPositiveButtonClickListener {
                // IF-condition to check if the date is ahead in time
                if(sharedViewModel.dateFormatter.parse(sharedViewModel.quitDate)!! > sharedViewModel.dateFormatter.parse(sharedViewModel.currentDate)) {
                    return@addOnPositiveButtonClickListener
                }
                inputDate.text = sharedViewModel.quitDate
            }
            }

            btnBack.setOnClickListener {
                if(inputUnits.text.toString() != ""){
                    sharedViewModel.setUnitQuantity(inputUnits.text.toString().toInt())
                }
                if(inputCost.text.toString() != ""){
                    sharedViewModel.setCostPerUnit(inputCost.text.toString().toInt())
                }

                lifecycleScope.launchWhenResumed {
                    sharedViewModel.saveLocal("unit",sharedViewModel.unitPerWeek.value.toString())
                    sharedViewModel.saveLocal("cost", sharedViewModel.costPerUnit.value.toString())
                    sharedViewModel.saveLocal("date", sharedViewModel.quitDate)
                    findNavController().safelyNavigate(R.id.action_settingsFragment_to_homeFragment)
                }
            }
        }
        return fragmentBinding.root
    }
}