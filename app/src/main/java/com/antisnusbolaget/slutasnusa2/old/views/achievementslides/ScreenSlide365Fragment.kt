package com.antisnusbolaget.slutasnusa2.old.views.achievementslides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide365Binding


import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel

class ScreenSlide365Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide365Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentScreenSlide365Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            // Setting values to textViews
            twDaysLeftAchievement365.text = sharedViewModel.daysLeftAchievement(365).toString()
            twMoneySavedAchievement365.text = sharedViewModel.moneySavedAchievement(365, twMoneySavedAchievement365).toString()
        }
        return fragmentBinding.root
    }
}