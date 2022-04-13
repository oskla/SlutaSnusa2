package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.antisnusbolaget.slutasnusa2.databinding.FragmentCostBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel


class CostFragment : Fragment() {


    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentCostBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCostBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Prevents multiple navController calls
        fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
            try { navigate(resId, args) }
            catch (e: Exception) { (e) }


        binding?.apply {

            btnGoToNext.setOnClickListener{
                findNavController().safelyNavigate(R.id.action_costFragment_to_dateFragment)
            }


        }
    }
}
