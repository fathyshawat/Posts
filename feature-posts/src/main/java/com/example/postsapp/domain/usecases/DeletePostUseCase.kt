package com.example.postsapp.domain.usecases

import com.example.postsapp.coroutine_dispatchers.IoDispatcher
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.data.repository.LocalRepository
import com.example.postsapp.data.repository.RemoteRepository
import com.example.postsapp.domain.workManger.IWorkManger
import com.example.postsapp.network.NetworkCheckConnection
import com.example.postsapp.presentation.DELETE
import com.example.postsapp.usecases.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val networkCheckConnection: NetworkCheckConnection,
    private val workManger: IWorkManger,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, PostsModel>(ioDispatcher) {

    override fun execute(parameters: Int): Flow<PostsModel> = flow {

        if (networkCheckConnection.checkConnection()) {
            val result: Response<ResponseBody> = remoteRepository.deletePost(parameters)
            if (result.isSuccessful)
                localRepository.deletePost(parameters)

            emit(
                PostsModel(
                    success = true,
                    id = parameters
                )
            )
        } else {
            workManger.enqueueWork(
                id = parameters,
                action = DELETE
            )
            emit(
                PostsModel(
                    success = false,
                    id = parameters
                )
            )
        }
    }

}