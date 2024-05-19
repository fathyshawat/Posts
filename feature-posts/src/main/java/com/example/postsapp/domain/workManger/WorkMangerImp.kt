package com.example.postsapp.domain.workManger

import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class WorkMangerImp @Inject constructor(@ApplicationContext private val context: Context) :
    IWorkManger {


    override fun enqueueWork(id: Int, title: String?, body: String?, userID: Int?, action: String) {
        val constraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val inputData = Data.Builder().putString("action", action).putString("title", title)
            .putString("body", body).putInt("id", id).putInt("userID", userID ?: 1).build()

        val myWorkRequest = OneTimeWorkRequestBuilder<PostsWorkManger>()
            .setConstraints(constraints)
            .setInputData(inputData).build()

        WorkManager.getInstance(context).enqueue(myWorkRequest)
    }
}