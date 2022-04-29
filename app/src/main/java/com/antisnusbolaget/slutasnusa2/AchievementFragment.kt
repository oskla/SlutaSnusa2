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

    var fragmentsList = listOf<Fragment>(ScreenSlide14Fragment(),ScreenSlideFragment(), ScreenSlide2Fragment())

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

    val days14 = sharedViewModel.daysLeftAchievement(14)
    val days30 = sharedViewModel.daysLeftAchievement(30)
    val days45 = sharedViewModel.daysLeftAchievement(45)
    

    if (days14 == 0) {
        viewPager.post {
            viewPager.setCurrentItem(1, true)
        }
    } else if (days30 == 0) {
        viewPager.post {
            viewPager.setCurrentItem(2, true)
        }
    } else if (days45 == 0) {
        viewPager.post {
            viewPager.setCurrentItem(3, true)
        }
    }


}
        return fragmentBinding.root
    }

    private inner class ScreenSlideAdapter(fa: AchievementFragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentsList.size

        override fun createFragment(position: Int): Fragment = fragmentsList[position]
    }



}