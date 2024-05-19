package com.example.postsapp.domain.workManger

interface IWorkManger {
    fun enqueueWork(
        id: Int,
        title: String? = null,
        body: String? = null,
        userID: Int? = null,
        action: String
    )

}