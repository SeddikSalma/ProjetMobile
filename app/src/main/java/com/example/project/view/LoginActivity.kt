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
    private lateinit var loadingProgressBar1: ProgressBar
    private lateinit var login : AppCompatButton

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            LoginActivityViewModelFactory()
        )[LoginActivityViewModel::class.java]

        loadingProgressBar1 = findViewById(R.id.loadingProgressBar1)
        login=findViewById(R.id.login)

        viewModel.getLoginResult().observe(this) {
            when(it){
                is LoginState.Idle -> {
                    Log.d("LoginTest", "Idle state")
                    login.isEnabled = true
                }
                is LoginState.Loading -> {
                    Log.d("LoginTest", "Loading state")
                    loadingProgressBar1.visibility = View.VISIBLE
                }
                is LoginState.Success -> {
                    Log.d("LoginTest", "Success state")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    SessionManager.saveAuthSession(it.tokens)
                   loadingProgressBar1.visibility = View.GONE

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
                    loadingProgressBar1.visibility = View.GONE

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

            login.isEnabled = false

            viewModel.loginUser(LoginRequestBody(email, password))
        }

    }

}
