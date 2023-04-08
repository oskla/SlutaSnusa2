package com.antisnusbolaget.slutasnusa2.old.views.achievementslides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide30Binding
import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel

class ScreenSlide30Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide30Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentScreenSlide30Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {

            // Setting values to textViews
            twDaysLeftAchievement.text = sharedViewModel.daysLeftAchievement(30).toString()
            twMoneySavedAchievement.text = sharedViewModel.moneySavedAchievement(
                30,
                twMoneySavedAchievement,

            ).toString()
        }
        return fragmentBinding.root
    }
}