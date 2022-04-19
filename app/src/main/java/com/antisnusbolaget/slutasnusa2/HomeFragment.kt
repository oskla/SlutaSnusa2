package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.antisnusbolaget.slutasnusa2.databinding.FragmentHomeBinding
import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class HomeFragment : Fragment() {

    private val sharedViewModel: UserViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null
    private lateinit var myDb: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        activity?.let { FirebaseApp.initializeApp(it) }

        myDb = FirebaseDatabase.getInstance("https://slutasnusa-ad847-default-rtdb.europe-west1.firebasedatabase.app/").getReference("data")
        sharedViewModel.dbWrite(myDb)


        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

           // sharedViewModel.dbInit(context)


            twDaysWithout.text = sharedViewModel.daysWithout.value.toString()
            twMoneySaved.text = sharedViewModel.totalMoneySaved.value.toString()

        }
    }
}