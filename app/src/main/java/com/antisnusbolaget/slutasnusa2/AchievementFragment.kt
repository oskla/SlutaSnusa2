package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.antisnusbolaget.slutasnusa2.databinding.FragmentAchievementBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class AchievementFragment : Fragment() {
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentAchievementBinding? = null
    private lateinit var viewPager: ViewPager2

    var fragmentsList = listOf<Fragment>(
        ScreenSlide7Fragment(),
        ScreenSlide14Fragment(),
        ScreenSlide30Fragment(),
        ScreenSlide45Fragment(),
        ScreenSlide365Fragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentAchievementBinding.inflate(inflater, container, false)
        binding = fragmentBinding


        viewPager = binding!!.root.findViewById(R.id.viewPager)
        val pagerAdapter = ScreenSlideAdapter(this)
        viewPager.adapter = pagerAdapter


        binding.apply {

            when(true){
                (sharedViewModel.daysWithout.value!! in 0..6) -> {viewPager.post{viewPager.setCurrentItem(0, false)}}
                (sharedViewModel.daysWithout.value!! in 7..13) -> {viewPager.post{viewPager.setCurrentItem(1, true)}}
                (sharedViewModel.daysWithout.value!! in 14..29) -> {viewPager.post{viewPager.setCurrentItem(2, true)}}
                (sharedViewModel.daysWithout.value!! in 30..44) -> {viewPager.post{viewPager.setCurrentItem(3, true)}}
                (sharedViewModel.daysWithout.value!! in 45..364) -> {viewPager.post{viewPager.setCurrentItem(4, true)}}
            }


        }
        return fragmentBinding.root
    }

    private inner class ScreenSlideAdapter(fa: AchievementFragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentsList.size

        override fun createFragment(position: Int): Fragment = fragmentsList[position]
    }
}