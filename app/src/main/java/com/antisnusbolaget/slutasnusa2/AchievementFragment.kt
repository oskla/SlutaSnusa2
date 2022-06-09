package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentAchievementBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel

class AchievementFragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentAchievementBinding? = null

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
        val fragmentBinding = FragmentAchievementBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        binding?.apply {
            // Show statisticsFragment if goal exists
            if (sharedViewModel.goalExists) {
                fragmentContainerGoal.isVisible = true
                btnAdd.isVisible = false
                twGoalnotreached.isVisible = false
            } else {
                fragmentContainerGoal.isVisible = false
                btnAdd.isVisible = true
                twGoalnotreached.isVisible = true
            }


            btnAdd.setOnClickListener{
                // When adding goal set goalExists to true
                sharedViewModel.goalExists = true
                lifecycleScope.launchWhenResumed { // Prevents multiple navController calls

                    findNavController().safelyNavigate(R.id.action_achievementFragment_to_goalFragment)

                }
            }
        }
        return fragmentBinding.root
    }

}