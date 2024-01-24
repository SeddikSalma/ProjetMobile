package com.example.project.dataclasses.create_post

import com.google.gson.annotations.SerializedName

data class CreatePostBody (
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
)