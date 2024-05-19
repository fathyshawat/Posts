package com.example.postsapp.presentation

sealed class PostsValidation {

  data object DataValid : PostsValidation()
  data object InvalidData : PostsValidation()
}