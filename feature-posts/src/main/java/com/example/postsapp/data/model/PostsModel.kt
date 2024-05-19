package com.example.postsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsModel(
    val success: Boolean = false,
    val id: Int = 0,
    val title: String? = null,
    val body: String? = null,
    val userID: Int? = 0,
) : Parcelable