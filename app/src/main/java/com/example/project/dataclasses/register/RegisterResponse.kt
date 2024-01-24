package com.example.project.dataclasses.register

import com.example.project.dataclasses.User
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("user")
    val user : User
)
