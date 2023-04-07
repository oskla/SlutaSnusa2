package com.antisnusbolaget.slutasnusa2.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.databinding.FragmentSplashBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        //Function call to read data
        sharedViewModel.readLocal("unit")
        sharedViewModel.readLocal("cost")
        sharedViewModel.readLocal("date")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSplashBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Splash animation
        splashIcon = binding?.splashIcon ?: ImageView(context)
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        navBar?.isVisible=false

        splashIcon.visibility = View.GONE
        
        fadeAnimationSplash()

        return fragmentBinding.root
    }

    private fun fadeAnimationSplash() {
        splashIcon.animate()
            .alpha(1f)
            .setDuration(2000)
            .setListener(object: AnimatorListenerAdapter(){

                // On start
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    splashIcon.visibility = View.VISIBLE
                    //loading our custom made animations
                    val animationFade = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    //starting the animation
                    splashIcon.startAnimation(animationFade)
                }

                // On end
                override fun onAnimationEnd(animation: Animator) {
                    // Is this the first time starting app?
                    if (sharedViewModel.unitPerWeek.value != 0) { //NO
                        lifecycleScope.launchWhenResumed {
                            findNavController().safelyNavigate(R.id.action_splashFragment_to_homeFragment)
                        }
                    } else { // YES
                        lifecycleScope.launchWhenResumed {
                            findNavController().safelyNavigate(R.id.action_splashFragment_to_unitFragment)
                        }
                    }
                }
            }
        )
    }
}






