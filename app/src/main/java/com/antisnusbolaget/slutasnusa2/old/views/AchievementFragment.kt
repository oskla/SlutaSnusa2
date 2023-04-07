package com.antisnusbolaget.slutasnusa2.old.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.databinding.FragmentAchievementBinding
import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel
import com.antisnusbolaget.slutasnusa2.old.views.achievementslides.ScreenSlide14Fragment
import com.antisnusbolaget.slutasnusa2.old.views.achievementslides.ScreenSlide30Fragment
import com.antisnusbolaget.slutasnusa2.old.views.achievementslides.ScreenSlide365Fragment
import com.antisnusbolaget.slutasnusa2.old.views.achievementslides.ScreenSlide45Fragment
import com.antisnusbolaget.slutasnusa2.old.views.achievementslides.ScreenSlide7Fragment

class AchievementFragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentAchievementBinding? = null
    private lateinit var viewPager: ViewPager2

    var fragmentsList = listOf(
        ScreenSlide7Fragment(),
        ScreenSlide14Fragment(),
        ScreenSlide30Fragment(),
        ScreenSlide45Fragment(),
        ScreenSlide365Fragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentAchievementBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Instantiate viewpager
        viewPager = binding!!.root.findViewById(R.id.viewPager)
        val pagerAdapter = ScreenSlideAdapter(this)
        viewPager.adapter = pagerAdapter

        binding.apply {
            when(true){ // Checks which card to start on
                (sharedViewModel.daysWithout.value!! in 0..6) -> {viewPager.post{viewPager.setCurrentItem(0, false)}}
                (sharedViewModel.daysWithout.value!! in 7..13) -> {viewPager.post{viewPager.setCurrentItem(1, true)}}
                (sharedViewModel.daysWithout.value!! in 14..29) -> {viewPager.post{viewPager.setCurrentItem(2, true)}}
                (sharedViewModel.daysWithout.value!! in 30..44) -> {viewPager.post{viewPager.setCurrentItem(3, true)}}
                (sharedViewModel.daysWithout.value!! in 45..364) -> {viewPager.post{viewPager.setCurrentItem(4, true)}}
                else -> {}
            }
        }
        return fragmentBinding.root
    }

            //Class to create pages in viewpager based on fragments in array
    private inner class ScreenSlideAdapter(fa: AchievementFragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentsList.size
        override fun createFragment(position: Int): Fragment = fragmentsList[position]
    }
}