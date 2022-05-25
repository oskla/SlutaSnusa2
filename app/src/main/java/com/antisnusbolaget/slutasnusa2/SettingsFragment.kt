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
          //  sharedViewModel.unitPerWeek.value?.let { inputUnits.setHint(it) }

            var unit = inputUnits.text
            val unitToString = twInputUnits.text.toString()

            twInputUnits.text = sharedViewModel.unitPerWeek.value.toString()


                twInputUnits.setOnClickListener{
                    viewSwitcher.showNext()
                }

            inputUnits.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    unit = inputUnits.text
                    twInputUnits.text = unit
                    viewSwitcher.showNext()

                }
                false
            }


            btnBack.setOnClickListener {


                if (unit.toString() != "") {

                    sharedViewModel.setUnitQuantity(unitToString.toInt())
                    sharedViewModel.saveLocal("unit",sharedViewModel.unitPerWeek.value.toString())
                }

                lifecycleScope.launchWhenResumed {
                    findNavController().safelyNavigate(R.id.action_settingsFragment_to_homeFragment)
                }

            }




        }

        return fragmentBinding.root
    }
}