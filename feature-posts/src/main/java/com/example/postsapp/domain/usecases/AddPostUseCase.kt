package com.example.postsapp.domain.usecases

import com.example.postsapp.coroutine_dispatchers.IoDispatcher
import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.data.repository.LocalRepository
import com.example.postsapp.data.repository.RemoteRepository
import com.example.postsapp.domain.workManger.IWorkManger
import com.example.postsapp.network.NetworkCheckConnection
import com.example.postsapp.presentation.CREATE
import com.example.postsapp.usecases.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddPostUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val networkCheckConnection: NetworkCheckConnection,
    private val workManger: IWorkManger,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<PostRequestModel, PostsModel>(ioDispatcher) {

    override fun execute(parameters: PostRequestModel): Flow<PostsModel> = flow {

        if (networkCheckConnection.checkConnection()) {
            val result = remoteRepository.addPost(parameters)
            if (result.success)
                localRepository.addPost(parameters)
            emit(result)
        } else {

            workManger.enqueueWork(
                parameters.id ?: 1,
                parameters.title ?: "",
                parameters.body ?: "",
                parameters.userID ?: 0,
                CREATE
            )
            emit(
                PostsModel(
                    success = false,
                    id = parameters.id?:1,
                    parameters.title ?: "",
                    parameters.body ?: "",
                    parameters.userID ?: 0,
                )
            )
        }
    }

}