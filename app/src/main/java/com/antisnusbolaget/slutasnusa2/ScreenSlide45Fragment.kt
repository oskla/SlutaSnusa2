package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide45Binding

import com.antisnusbolaget.slutasnusa2.model.UserViewModel


class ScreenSlide45Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide45Binding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentScreenSlide45Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {

            // Run method calculating days left to achievement

            twDaysLeftAchievement45.text = sharedViewModel.daysLeftAchievement(45).toString()
            twMoneySavedAchievement45.text = sharedViewModel.moneySavedAchievment(
                45,
                twMoneySavedAchievement45,
                moneySavedAchievment
            ).toString()


        }

        return fragmentBinding.root
    }



}