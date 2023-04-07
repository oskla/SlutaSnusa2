package com.antisnusbolaget.slutasnusa2.views

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.databinding.FragmentSettingsBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentSettingsBinding? = null

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Fragment.showKeyboard(view: View) {
        view.let { activity?.showKeyboard(view) }
    }

    private fun Context.showKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        navBar?.isVisible=false

        binding?.apply {

            inputUnits.hint = sharedViewModel.unitPerWeek.value.toString() + " "
            inputCost.hint = sharedViewModel.costPerUnit.value.toString() + " "
            inputDate.text = sharedViewModel.quitDate

            materialCardView.setOnClickListener {
                inputUnits.requestFocus()
                showKeyboard(inputUnits)
            }

            materialCardView2.setOnClickListener {
                inputCost.requestFocus()
                showKeyboard(inputCost)
            }

            // Hide keyboard and clear focus when done
            inputUnits.setOnEditorActionListener { _, keyCode, event ->

                if (((event?.action ?: -1) == KeyEvent.ACTION_UP)
                    || keyCode == EditorInfo.IME_ACTION_DONE) {

                    inputUnits.clearFocus()
                    hideKeyboard()
                }
                false
            }

            inputCost.setOnEditorActionListener { _, keyCode, event ->
                if (((event?.action ?: -1) == KeyEvent.ACTION_UP)
                    || keyCode == EditorInfo.IME_ACTION_DONE) {

                    inputCost.clearFocus()
                    hideKeyboard()
                }
                false
            }

            inputDate.setOnClickListener{
            val manager = childFragmentManager
            if (sharedViewModel.datePicker.isAdded) { null }else { sharedViewModel.calenderSelection(manager)} // Prevents multiple functions-calls / app crash
            //ClickListener on date-popup
            sharedViewModel.datePicker.addOnPositiveButtonClickListener {
                // IF-condition to check if the date is ahead in time
                if(sharedViewModel.dateFormatter.parse(sharedViewModel.quitDate)!! > sharedViewModel.currentDate?.let { it1 ->
                        sharedViewModel.dateFormatter.parse(
                            it1
                        )
                    }) {
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

            twSaveSettings.setOnClickListener {
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

                    sharedViewModel.settingsChanged = true
                }
            }

        }
        return fragmentBinding.root
    }
}