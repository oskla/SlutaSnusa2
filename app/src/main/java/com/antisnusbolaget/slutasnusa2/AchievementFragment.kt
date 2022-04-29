package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.antisnusbolaget.slutasnusa2.databinding.FragmentAchievementBinding


class AchievementFragment : Fragment() {
    private var binding: FragmentAchievementBinding? = null
    private lateinit var viewPager: ViewPager2

    var fragmentsList = listOf<Fragment>(ScreenSlideFragment(),ScreenSlide2Fragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

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

        return fragmentBinding.root
    }

    private inner class ScreenSlideAdapter(fa: AchievementFragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentsList.size

        override fun createFragment(position: Int): Fragment = fragmentsList[position]
    }



}