package com.example.postsapp.presentation.addUpdatePost

import androidx.lifecycle.viewModelScope
import com.example.postsapp.base.BaseViewModel
import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.domain.usecases.AddPostUseCase
import com.example.postsapp.domain.usecases.UpdatePostUseCase
import com.example.postsapp.network.Resource
import com.example.postsapp.presentation.CREATE
import com.example.postsapp.presentation.PostsValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class AddUpdateViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
) : BaseViewModel() {

    private val _addPostModel = MutableSharedFlow<Resource<PostsModel>>()

    val addPostModel: SharedFlow<Resource<PostsModel>>
        get() = _addPostModel


    private val _updatePostModel = MutableSharedFlow<Resource<PostsModel>>()

    val updatePostModel: SharedFlow<Resource<PostsModel>>
        get() = _updatePostModel

    private val _validation = MutableStateFlow<PostsValidation>(PostsValidation.DataValid)
    val validation: StateFlow<PostsValidation>
        get() = _validation


    fun addUpdatePost(
        title: String?,
        body: String?,
        action: String
    ) {
        if (title.isNullOrEmpty() || body.isNullOrEmpty()) {
            emitIfChanged(PostsValidation.InvalidData)
        } else {
            emitIfChanged(PostsValidation.DataValid)
            if (action == CREATE)
                addPostUseCase(
                    PostRequestModel(title = title, body = body)
                ).onEach {
                    _addPostModel.emit(it)
                }.launchIn(viewModelScope)
            else
                updatePostUseCase(
                    PostRequestModel(
                        title = title, body = body
                    )
                ).onEach {
                    _updatePostModel.emit(it)
                }.launchIn(viewModelScope)
        }
    }


    private fun emitIfChanged(newValue: PostsValidation) {
        if (_validation.value != newValue) {
            _validation.value = newValue
        }
    }

}