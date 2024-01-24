package com.example.project.dataclasses

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String,
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("email")
    val email: String,
)
