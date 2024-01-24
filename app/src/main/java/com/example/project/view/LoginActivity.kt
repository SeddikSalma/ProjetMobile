package com.example.project.view
import android.app.AlertDialog
import SessionManager
import android.content.Intent
import android.os.Bundle;
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.ActivityLoginBinding
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.view_model.LoginActivityViewModel
import com.example.project.view_model.factory.LoginActivityViewModelFactory
import com.example.project.view_model.LoginState


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel : LoginActivityViewModel

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            LoginActivityViewModelFactory()
        )[LoginActivityViewModel::class.java]

        viewModel.getLoginResult().observe(this) {
            when(it){
                is LoginState.Idle -> {
                    Log.d("LoginTest", "Idle state")
                    binding.login.isEnabled = true
                }
                is LoginState.Loading -> {
                    Log.d("LoginTest", "Loading state")
                    binding.loadingProgressBar1.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                    Log.d("LoginTest", "Success state")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    SessionManager.saveAuthSession(it.tokens)
                    binding.loadingProgressBar1.visibility = View.GONE

                }
                is LoginState.Error -> {
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage(it.error)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                    Log.d("RegisterTest", it.error)
                    binding.loadingProgressBar1.visibility = View.GONE
                }
            }
        } 

        binding.signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener{
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            binding.login.isEnabled = false
            viewModel.loginUser(LoginRequestBody(email, password))
        }
    }

}
