package com.example.postsapp.presentation.mainPosts

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.postsapp.base.BaseViewModel
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.domain.usecases.DeletePostUseCase
import com.example.postsapp.domain.usecases.GetPostsUseCase
import com.example.postsapp.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainPostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : BaseViewModel() {


    private val _postsModel = MutableSharedFlow<Resource<List<PostsModel?>?>>()

    val postsModel: SharedFlow<Resource<List<PostsModel?>?>>
        get() = _postsModel


    private val _deletePostModel = MutableSharedFlow<Resource<PostsModel>>()

    val deletePostModel: SharedFlow<Resource<PostsModel>>
        get() = _deletePostModel


    fun getPosts() {
        getPostsUseCase(Unit)
            .onEach {
                _postsModel.emit(it)
            }.launchIn(viewModelScope)
    }

    fun deletePost(id: Int) {
        deletePostUseCase(id)
            .onEach {
                _deletePostModel.emit(it)
            }.launchIn(viewModelScope)
    }

}