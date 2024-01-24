package com.example.project.dataclasses.login

import com.google.gson.annotations.SerializedName

data class LoginRequestBody (
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,
)