package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide365Binding


import com.antisnusbolaget.slutasnusa2.model.UserViewModel


class ScreenSlide365Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide365Binding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentScreenSlide365Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {

            // Run method calculating days left to achievement

            twDaysLeftAchievement365.text = sharedViewModel.daysLeftAchievement(365, ).toString()
            twMoneySavedAchievement365.text = sharedViewModel.moneySavedAchievment(365, twMoneySavedAchievement365).toString()


        }

        return fragmentBinding.root
    }



}