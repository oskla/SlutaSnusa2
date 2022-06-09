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
import com.antisnusbolaget.slutasnusa2.databinding.FragmentStatisticsBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.Hold

class StatisticsFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentStatisticsBinding? = null


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
        val fragmentBinding = FragmentStatisticsBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        navBar?.isVisible=false

        binding?.apply {
            twHeadingGoal.text = sharedViewModel.goalName

        }




        return fragmentBinding.root
    }

}