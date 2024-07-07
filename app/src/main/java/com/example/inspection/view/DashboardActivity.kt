package com.example.inspection.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inspection.R
import com.example.inspection.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private var email : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.title = "Inspection"


        binding.bottomNavigation.setOnItemSelectedListener() {
            item ->  when(item.itemId) {
            R.id.draft_inspection_menu -> {
                loadFragment(DraftInspectionFragment())
                true
            }
            R.id.completed_inspection_menu -> {
                loadFragment(CompletedInspectionFragment())
                true
            }
            R.id.user_profile -> {
                email = intent.getStringExtra("email").toString()
                val bundle = Bundle()
                bundle.putString("email", email)
                val userProfileFragment = UserProfileFragment()
                userProfileFragment.arguments = bundle
                loadFragment(userProfileFragment)
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