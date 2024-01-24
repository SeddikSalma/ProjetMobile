package com.example.project.dataclasses

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
)