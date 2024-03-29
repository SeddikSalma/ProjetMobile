package com.example.project.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.ActivitySignupBinding
import com.example.project.dataclasses.register.RegisterBody
import com.example.project.ui.NotConnectedActivity
import com.example.project.ui.login.LoginActivity
import com.example.project.utils.ConnectionManager
import com.example.project.utils.showErrorDialog

class SignupActivity : AppCompatActivity(), View.OnKeyListener {

   private lateinit var binding : ActivitySignupBinding
   private lateinit var viewModel : SignupActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        ConnectionManager.isConnected.observe(this) {
            when(it) {
                true -> {

                }
                false -> {
                    val intent = Intent(this, NotConnectedActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            }
        }

        setupValidationListeners()

        binding.signup.setOnClickListener{
            binding.signup.isEnabled = false
            onSubmit()
        }

        viewModel = ViewModelProvider(
            this,
            SignupActivityViewModelFactory(),
        )[SignupActivityViewModel::class.java]

        viewModel.getRegisterResult().observe(this) {
            when(it){
                is RegisterState.Idle -> {
                    binding.signup.isEnabled = true
                    Log.d("RegisterTest", "Idle state")
                }
                is RegisterState.Loading -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                    Log.d("RegisterTest", "Loading state")

                }
                is RegisterState.Success -> {
                    binding.signup.isEnabled = true
                    binding.loadingProgressBar.visibility = View.GONE
                    Log.d("RegisterTest", "Success state")
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }
                is RegisterState.Error -> {
                    binding.signup.isEnabled = true
                    binding.loadingProgressBar.visibility = View.GONE
                    showErrorDialog(this, it.error)
                    Log.d("RegisterTest", it.error)
                }
            }
        }
    }

    private fun setupValidationListeners(){
        binding.fullNameEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validateFullName()
        }
        binding.emailEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validateEmail()
        }
        binding.passwordEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) validatePassword()
        }

        binding.passwordEt.addTextChangedListener {
            binding.passwordTil.error = null;
        }

        binding.emailEt.addTextChangedListener {
            binding.emailTil.error = null;
        }

        binding.fullNameEt.addTextChangedListener {
            binding.fullNameTil.error = null;
        }
    }

    private fun validateFullName() : Boolean {
        val value: String = binding.fullNameEt.text.toString()

        if (value.isEmpty()) {
            binding.fullNameTil.apply {
                isErrorEnabled=true
                error="Fullname required"
            }
            return false;
        }
        return true;
    }
    private fun validateEmail() : Boolean{
        val value: String=binding.emailEt.text.toString()

        if(value.isEmpty()){
            binding.emailTil.apply {
                isErrorEnabled=true
                error="Email required"
            }
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            binding.emailTil.apply {
                isErrorEnabled=true
                error="Email address is invalid"
            }
            return false;
        }
        return true;
    }

    private fun validatePassword() : Boolean{
        val value: String=binding.passwordEt.text.toString()

        if(value.isEmpty()){
            binding.passwordTil.apply {
                isErrorEnabled=true
                error="Password required"
            }
            return false;
        }

        if(value.length<6){
            binding.passwordTil.apply {
                isErrorEnabled=true
                error="Password must be 6 characters long"
            }
            return false;
        }
        return true;
    }

    private fun validate(): Boolean {
        var isValid: Boolean = validateFullName();
        isValid = isValid && validateEmail();
        isValid = isValid && validatePassword();
        return isValid;
    }

    private fun onSubmit(){
        if(validate()){
            viewModel.registerUser(
                RegisterBody(
                    binding.fullNameEt.text!!.toString(),
                    binding.emailEt.text!!.toString(),
                    binding.passwordEt.text!!.toString()
                )
            )
        }
    }

    override fun onKey(view: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
        if(KeyEvent.KEYCODE_ENTER == keyCode && keyEvent!!.action == KeyEvent.ACTION_UP){
            onSubmit()
        }
        return false
    }
}
