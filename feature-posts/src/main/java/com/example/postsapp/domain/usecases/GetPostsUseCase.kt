package com.example.postsapp.domain.usecases

import android.util.Log
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.data.repository.LocalRepository
import com.example.postsapp.data.repository.RemoteRepository
import com.example.postsapp.domain.mapper.PostRequestModelMapper
import com.example.postsapp.coroutine_dispatchers.IoDispatcher
import com.example.postsapp.network.NetworkCheckConnection
import com.example.postsapp.usecases.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val networkCheckConnection: NetworkCheckConnection,
    private val postRequestModelMapper: PostRequestModelMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Unit, List<PostsModel?>?>(ioDispatcher) {

    override fun execute(parameters: Unit): Flow<List<PostsModel?>?> = flow {
        if (networkCheckConnection.checkConnection()) {
            val result = remoteRepository.getPosts()
            if (result?.isNotEmpty() == true) {
                localRepository.deleteAll()
                result.forEach { item ->
                    item?.let { localRepository.addPost(postRequestModelMapper.map(it)) }
                }
            }
            emit(result)
        } else {
            emit(localRepository.getPosts())
        }
    }
}