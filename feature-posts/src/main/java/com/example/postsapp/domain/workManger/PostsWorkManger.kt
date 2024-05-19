package com.example.postsapp.domain.workManger

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.domain.usecases.AddPostUseCase
import com.example.postsapp.domain.usecases.DeletePostUseCase
import com.example.postsapp.domain.usecases.UpdatePostUseCase
import com.example.postsapp.presentation.ACTION
import com.example.postsapp.presentation.BODY
import com.example.postsapp.presentation.CREATE
import com.example.postsapp.presentation.DELETE
import com.example.postsapp.presentation.ID
import com.example.postsapp.presentation.TITLE
import com.example.postsapp.presentation.UPDATE
import com.example.postsapp.presentation.USER_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi


@HiltWorker
class PostsWorkManger @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val addPostUseCase: AddPostUseCase,
    @Assisted private val deletePostUseCase: DeletePostUseCase,
    @Assisted private val updatePostUseCase: UpdatePostUseCase
) : Worker(appContext, workerParams) {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun doWork(): Result {

        val action = inputData.getString(ACTION)
        val id = inputData.getInt(ID, 0)
        val userID = inputData.getInt(USER_ID, 0)
        val title = inputData.getString(TITLE)
        val body = inputData.getString(BODY)

        when (action) {
            UPDATE -> {
                updatePostUseCase(
                    PostRequestModel(
                        id, title, body, userID
                    )
                )

            }

            DELETE -> {
                deletePostUseCase(
                    id
                )
            }

            CREATE -> {
                addPostUseCase(
                    PostRequestModel(
                        id, title, body, userID
                    )
                )
            }
        }




        return Result.success()
    }
}