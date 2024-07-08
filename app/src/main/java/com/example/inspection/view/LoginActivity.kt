package com.example.inspection.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.inspection.databinding.ActivityLoginBinding
import com.example.inspection.room.entity.User
import com.example.inspection.utils.AppUtils
import com.example.inspection.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(){

    private val  loginViewModel by lazy {
        LoginViewModel(this.application)
    }

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.title = "Login"

        binding.btnLogin.setOnClickListener {

            if(!validateInputs()){
                return@setOnClickListener
            }

            if(AppUtils.isNetworkAvailable(this)){
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.callLoginAPI(email, password)

            loginViewModel.loginResponseCd.observe(this, Observer {
                Log.d("LoginActivity", "Response: $it")
                if(it==200){
                    val user = User(0, email, password)
                    loginViewModel.insert(user)
                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "User does not exist or the credentials are incorrect", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
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


}