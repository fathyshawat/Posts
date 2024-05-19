package com.example.postsapp.domain.workManger

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.postsapp.presentation.ACTION
import com.example.postsapp.presentation.BODY
import com.example.postsapp.presentation.ID
import com.example.postsapp.presentation.TITLE
import com.example.postsapp.presentation.USER_ID
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class WorkMangerImp @Inject constructor(@ApplicationContext private val context: Context) :
    IWorkManger {


    override fun enqueueWork(id: Int, title: String?, body: String?, userID: Int?, action: String) {
        val constraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val inputData = Data.Builder().putString(ACTION, action).putString(TITLE, title)
            .putString(BODY, body).putInt(ID, id).putInt(USER_ID, userID ?: 1).build()

        val myWorkRequest = OneTimeWorkRequestBuilder<PostsWorkManger>()
            .setConstraints(constraints)
            .setInputData(inputData).build()

        WorkManager.getInstance(context).enqueue(myWorkRequest)
    }
}