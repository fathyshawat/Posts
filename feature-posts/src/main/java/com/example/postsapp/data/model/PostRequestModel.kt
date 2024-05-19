package com.example.postsapp.data.model

data class PostRequestModel(
    val id: Int? = 1,
    val title: String?,
    val body: String?,
    val userID: Int? = 1
)