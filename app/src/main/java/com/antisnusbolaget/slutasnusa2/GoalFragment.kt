package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentDateBinding
import com.antisnusbolaget.slutasnusa2.databinding.FragmentGoalBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.Hold

class GoalFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentGoalBinding? = null

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
        val fragmentBinding = FragmentGoalBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        navBar?.isVisible=false

        binding?.apply {
            inputGoalName
            inputGoalCost

            fun switchCheckedBox(v : View) {
                when (v.id) {
                    R.id.checkboxNo -> checkboxNo.isChecked = false
                    R.id.checkboxYes -> checkboxYes.isChecked = false
                }
            }

            checkboxNo.setOnClickListener{
                switchCheckedBox(checkboxYes)
            }

            checkboxYes.setOnClickListener{
                switchCheckedBox(checkboxNo)
            }

        }




        return fragmentBinding.root
    }

}