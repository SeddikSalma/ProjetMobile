package com.example.project.dataclasses

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("user")
    val user: User
)