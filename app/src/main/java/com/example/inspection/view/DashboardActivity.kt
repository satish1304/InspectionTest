package com.example.inspection.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inspection.R
import com.example.inspection.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener() {
            item ->  when(item.itemId) {
            R.id.draft_inspection_menu -> {
                loadFragment(DraftInspectionFragment())
                true
            }
            R.id.completed_inspection_menu -> {
                // Respond to navigation item 2 click
                loadFragment(CompletedInspectionFragment())
                true
            }
            R.id.upcoming_inspection_menu -> {
                // Respond to navigation item 2 click
                loadFragment(UpcomingInspectionFragment())
                true
            }

            else -> false
          }
        }

        loadFragment(DraftInspectionFragment())

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}