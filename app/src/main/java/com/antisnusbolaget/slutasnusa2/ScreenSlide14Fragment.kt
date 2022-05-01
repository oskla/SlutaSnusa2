package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlide14Binding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel


class ScreenSlide14Fragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlide14Binding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentScreenSlide14Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {


            twDaysLeftAchievement14.text = sharedViewModel.daysLeftAchievement(14).toString()
            twMoneySavedAchievement14.text = sharedViewModel.moneySavedAchievment(14).toString()


        }

        return fragmentBinding.root
    }



}