package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentTabBinding
import com.antisnusbolaget.slutasnusa2.databinding.FragmentUnitBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel

class TabFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentTabBinding? = null

    // Prevents multiple navController calls
    private fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
        try { navigate(resId, args) }
        catch (e: Exception) { (e) }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentTabBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            bottomNavigation.setOnItemSelectedListener {item ->
                when(item.itemId) {
                    R.id.homeFragment -> {

                        println("Home tab pushed")
                        true
                    }
                    R.id.achievementFragment -> {

                        println("Achievement tab pushed")
                        true
                    }
                    else -> { false}
                }

            }
        }

    }
}