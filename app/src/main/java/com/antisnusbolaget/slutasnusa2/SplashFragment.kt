package com.antisnusbolaget.slutasnusa2

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentCostBinding
import com.antisnusbolaget.slutasnusa2.databinding.FragmentSplashBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import java.text.NumberFormat
import java.util.*

class SplashFragment : Fragment() {

    private var shortAnimationDuration: Int = 0
    private lateinit var splashIcon: ImageView

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentSplashBinding? = null


    // Prevents multiple navController calls
    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.readLocal("unit")
        sharedViewModel.readLocal("cost")
        sharedViewModel.readLocal("date")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSplashBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        splashIcon = binding?.splashIcon ?: ImageView(context)
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        splashIcon.visibility = View.GONE

        // This will print at app-start (remove later)
        println("Units: ${sharedViewModel.unitPerWeek.value}")
        println("Cost: ${sharedViewModel.costPerUnit.value}")
        println("Quit date: ${sharedViewModel.quitDate}")


        fade()


        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

        }
    }

    private fun fade() {

        splashIcon.animate()
            .alpha(1f)
            .setDuration(2000)
            .setListener(object: AnimatorListenerAdapter(){
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    splashIcon.visibility = View.VISIBLE
                    //loading our custom made animations
                    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    //starting the animation
                    splashIcon.startAnimation(animation)

                }
                override fun onAnimationEnd(animation: Animator) {
                    println("animation ended")

                    // Is this the first time starting app?
                        //NO
                    if (sharedViewModel.unitPerWeek.value != 0) {
                        lifecycleScope.launchWhenResumed {
                            findNavController().safelyNavigate(R.id.action_splashFragment_to_homeFragment)
                        }
                         // YES
                    } else {
                        lifecycleScope.launchWhenResumed {
                            findNavController().safelyNavigate(R.id.action_splashFragment_to_unitFragment)
                        }
                    }
                }
            }
        )
    }
}






