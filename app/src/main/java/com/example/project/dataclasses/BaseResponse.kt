package com.example.project.dataclasses

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
    @SerializedName("msg")
    val message: String,
    @SerializedName("data")
    val data: T?,
)