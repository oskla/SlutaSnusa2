package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.antisnusbolaget.slutasnusa2.databinding.FragmentScreenSlideBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel


class ScreenSlideFragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentScreenSlideBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentScreenSlideBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {

            // Run method calculating days left to achievement
            sharedViewModel.daysLeftAchievement(31)
            twDaysLeftAchievement.text = sharedViewModel.daysLeftAchievement.toString()
            twMoneySavedAchievment.text = sharedViewModel.moneySavedAchievement.toString()

        }

        return fragmentBinding.root
    }



}