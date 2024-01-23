package com.example.project.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.ActivitySignupBinding
import com.example.project.dataclasses.RegisterBody
import com.example.project.repository.AuthRepository
import com.example.project.utils.APIService
import com.example.project.view_model.SignupActivityViewModel
import com.example.project.view_model.SignupActivityViewModelFactory

class SignupActivity : AppCompatActivity(), View.OnClickListener,View.OnFocusChangeListener,View.OnKeyListener {

   private lateinit var binding : ActivitySignupBinding
   private lateinit var viewModel : SignupActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.fullNameEt.onFocusChangeListener=this
        binding.emailEt.onFocusChangeListener=this
        binding.passwordEt.onFocusChangeListener=this
        binding.signup.setOnClickListener(this)
        viewModel=ViewModelProvider(this,SignupActivityViewModelFactory(AuthRepository(APIService.getService()),application)).get(SignupActivityViewModel::class.java)
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.getIsLoading().observe(this){

        }
        viewModel.getErrorMessage().observe(this){

        }
        viewModel.getUser().observe(this){
            if(it != null ){
                startActivity(Intent(this,SplashScreen::class.java))
            }
        }
    }
    private fun validateFullName() : Boolean {
        var errorMessage: String? = null
        val value: String = binding.fullNameEt.text.toString()
        if (value.isEmpty()) {
            errorMessage = "FullName required"
        }
        if(errorMessage!=null){
            binding.fullNameTil.apply {
                isErrorEnabled=true
                error=errorMessage
            }
        }

        return errorMessage==null
    }
    private fun validateEmail() : Boolean{
        var errorMessage : String? =null
        val value: String=binding.emailEt.text.toString()
        if(value.isEmpty()){
            errorMessage="Email required"
        }else if(Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMessage="Email address is invalid"
        }

        if(errorMessage!=null){
            binding.emailTil.apply {
                isErrorEnabled=true
                error=errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePassword() : Boolean{
        var errorMessage : String? =null
        val value: String=binding.passwordEt.text.toString()
        if(value.isEmpty()){
            errorMessage="Password required"
        }else if(value.length<6){
            errorMessage="Password must be 6 characters long"
        }

        if(errorMessage!=null){
            binding.passwordTil.apply {
                isErrorEnabled=true
                error=errorMessage
            }
        }

        return errorMessage == null
    }


    override fun onClick(view: View?) {
        if(view != null && view.id == R.id.signup){
            onSubmit()
        }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view!=null){
            when(view.id){

                R.id.fullNameTil ->{
                        if (hasFocus) {
                        if (binding.fullNameTil.isErrorEnabled){
                            binding.fullNameTil.isErrorEnabled=false
                        }
                            }
                else{validateFullName()}}

                R.id.emailTil ->{
                    if (hasFocus) {
                        if (binding.emailTil.isErrorEnabled){
                            binding.emailTil.isErrorEnabled=false
                        }
                    }
                    else{validateEmail()}}

                R.id.passwordTil ->{
                    if (hasFocus) {
                        if (binding.passwordTil.isErrorEnabled){
                            binding.passwordTil.isErrorEnabled=false
                        }
                    }
                    else{
                       validatePassword()



                    }}

            }
        }
    }

    private fun validate():Boolean{
        var isValid =true
        if(!validateFullName()) isValid = false
        if(!validateEmail()) isValid = false
        if(!validatePassword()) isValid = false
        if(isValid) isValid = false
        return isValid
    }

    private fun onSubmit(){
        if(validate()){
            viewModel.registerUser(RegisterBody(binding.fullNameEt.text!!.toString(),
                binding.emailEt.text!!.toString(),binding.passwordEt.text!!.toString()
                ))
        }
    }

    override fun onKey(view: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {

        if(KeyEvent.KEYCODE_ENTER == keyCode && keyEvent!!.action == KeyEvent.ACTION_UP){
            onSubmit()
        }
        return false   }
}
