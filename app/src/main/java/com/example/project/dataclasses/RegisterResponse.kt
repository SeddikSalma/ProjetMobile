package com.example.project.dataclasses

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("user")
    val user :User
)
