package com.example.inspection.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.inspection.databinding.ActivityLoginBinding
import com.example.inspection.utils.AppUtils
import com.example.inspection.viewmodel.LoginViewModel

class RegisterActivity : AppCompatActivity(){

    private val  loginViewModel by lazy {
        LoginViewModel(this.application)
    }

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Register"


        binding.btnLogin.text = "Register"
        binding.tvRegister.visibility = android.view.View.GONE

        binding.btnLogin.setOnClickListener {

            if(!validateInputs()){
                return@setOnClickListener
            }

            if(!AppUtils.isNetworkAvailable(this)){
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.callRegisterAPI(email, password)

            loginViewModel.loginResponseCd.observe(this, Observer {
                Log.d("LoginActivity", "Response: $it")
                if(it==200){
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "User does not exist or the credentials are incorrect", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun validateInputs(): Boolean {
        if(binding.etEmail.text.toString().isEmpty()){
            binding.etEmail.error = "Email cannot be empty"
            return false
        }
        if(binding.etPassword.text.toString().isEmpty()){
            binding.etPassword.error = "Password cannot be empty"
            return false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button click (e.g., navigate up or finish the activity)
                onBackPressed()
                finish()
                return true
            }
            // Other menu items (if any)
            else -> return super.onOptionsItemSelected(item)
        }
    }
}