package com.antisnusbolaget.slutasnusa2


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        fun loadFragment(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        bottomNav.setOnItemSelectedListener(){item ->
            when(item.itemId){
                R.id.homeNav -> {
                    loadFragment(HomeFragment())
                    println("tjena")


                    true
                }
                R.id.economyNav -> {
                    loadFragment(AchievementFragment())
                    println("tjena")

                    true
                }
                else -> false
            }

        }



    }
}