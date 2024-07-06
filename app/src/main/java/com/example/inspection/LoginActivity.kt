package com.example.inspection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.inspection.databinding.ActivityLoginBinding
import com.example.inspection.room.entity.User
import com.example.inspection.view.DashboardActivity
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

        binding.btnLogin.setOnClickListener {

            if(!validateInputs()){
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
                return@setOnClickListener
            }

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.callLoginAPI(email, password)
            loginViewModel.loginResponseCd.observe(this, Observer {
                Log.d("LoginActivity", "Response: $it")
                if(it==200){
                    val user = User(0,email, password)
                    loginViewModel.insert(user)
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                    //Invalid user or password
                }
            })
        }

    }

    fun validateInputs(): Boolean {
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