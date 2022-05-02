package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide14Binding
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide7Binding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel


class ScreenSlide7Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide7Binding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentScreenSlide7Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {


            twDaysLeftAchievement7.text = sharedViewModel.daysLeftAchievement(7).toString()
            twMoneySavedAchievement7.text = sharedViewModel.moneySavedAchievment(7,twMoneySavedAchievement7).toString()



        }

        return fragmentBinding.root
    }



}