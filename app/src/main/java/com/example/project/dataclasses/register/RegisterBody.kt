package com.example.project.dataclasses.register

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("fullname")
    val fullName : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password :String
)
