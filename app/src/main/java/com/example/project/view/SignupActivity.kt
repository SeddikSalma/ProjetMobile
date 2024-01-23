package com.example.project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.example.project.R
import com.example.project.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity(), View.OnClickListener,View.OnFocusChangeListener,View.OnKeyListener {

   private lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.fullName.onFocusChangeListener=this
        binding.editTextEmail.onFocusChangeListener=this
        binding.editTextPassword.onFocusChangeListener=this
    }

    private fun validateFullName() : Boolean {
        var errorMessage: String? = null
        val value: String = binding.fullName.text.toString()
        if (value.isEmpty()) {
            errorMessage = "FullName required"
        }
        return errorMessage==null
    }
    private fun validateEmail() : Boolean{
        var errorMessage : String? =null
        val value: String=binding.editTextEmail.text.toString()
        if(value.isEmpty()){
            errorMessage="Email required"
        }else if(Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMessage="Email address is invalid"
        }
        return errorMessage == null
    }

    private fun validatePassword() : Boolean{
        var errorMessage : String? =null
        val value: String=binding.editTextPassword.text.toString()
        if(value.isEmpty()){
            errorMessage="Password required"
        }else if(value.length<6){
            errorMessage="Password must be 6 characters long"
        }
        return errorMessage == null
    }


    override fun onClick(view: View?) {
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view!=null){
            when(view.id){

                R.id.fullName ->{
                        if (hasFocus) {

                            }
                else{validateFullName()}}

                R.id.editTextEmail ->{
                    if (hasFocus) {
                    }
                    else{validateEmail()}}

                R.id.editTextPassword ->{
                    if (hasFocus) {
                    }
                    else{validatePassword()}}

            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
return false   }
}
