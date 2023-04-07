package com.antisnusbolaget.slutasnusa2.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.databinding.FragmentUnitBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel

class UnitFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentUnitBinding? = null
    private var tempUnit: Int = 1

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
        val fragmentBinding = FragmentUnitBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            twUnits.text = tempUnit.toString()
            btnMinus.setOnClickListener { // Units must be > 1, else return.
                if(tempUnit > 1){
                    tempUnit -= 1
                }
                else{
                    return@setOnClickListener
                }
                twUnits.text = tempUnit.toString()
            }

            btnPlus.setOnClickListener {
                tempUnit += 1
                twUnits.text = tempUnit.toString()
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
}




