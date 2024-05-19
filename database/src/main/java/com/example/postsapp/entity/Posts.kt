package com.example.postsapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Posts(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val title: String?,
    val body: String?,
    val userID: Int?,
)