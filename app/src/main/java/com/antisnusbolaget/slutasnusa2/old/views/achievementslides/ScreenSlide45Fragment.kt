package com.antisnusbolaget.slutasnusa2.old.views.achievementslides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide45Binding

import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel

class ScreenSlide45Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide45Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentScreenSlide45Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {

            // Setting values to textViews
            twDaysLeftAchievement45.text = sharedViewModel.daysLeftAchievement(45).toString()
            twMoneySavedAchievement45.text = sharedViewModel.moneySavedAchievement(
                45,
                twMoneySavedAchievement45,

            ).toString()
        }
        return fragmentBinding.root
    }
}