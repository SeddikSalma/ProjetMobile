package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.project.dataclasses.SignUpRequest

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val signUpButton = findViewById<Button>(R.id.signup)}}
/*
        signUpButton.setOnClickListener {
            // Implement sign-up logic here
            signUp()
        }
*/


/*
    private fun signUp() {
        val signUpRequest = SignUpRequest(
            usernameEditText.text.toString(),
            emailEditText.text.toString(),
            passwordEditText.text.toString()
        )

    val apiService = RetrofitClient.createApiService()
    val call = apiService.signUp(signUpRequest)

        call.enqueue(object : Callback<YourResponseClass> {
            override fun onResponse(call: Call<YourResponseClass>, response: Response<YourResponseClass>) {
                if (response.isSuccessful) {
                    // Handle successful sign-up
                    // For example, navigate to the user's profile or home screen
                } else {
                    // Handle error
                    // For example, show an error message to the user
                }
            }

            override fun onFailure(call: Call<YourResponseClass>, t: Throwable) {
                // Handle failure
                // For example, show a generic error message to the user
            }
        })

    }
}*/