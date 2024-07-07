package com.example.inspection.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inspection.databinding.FragmentUserProfileBinding
import com.example.inspection.viewmodel.InspectionViewModel

class UserProfileFragment : Fragment() {

    private lateinit var binding : FragmentUserProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserProfileBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }

    private fun init(){
        val email = requireActivity().intent.getStringExtra("email").toString()
        binding.tvRegisterEmail.text = "Registered Email : $email"
        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}