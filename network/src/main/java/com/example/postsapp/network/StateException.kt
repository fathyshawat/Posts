package com.example.postsapp.network

fun stateException(errorMessage: String? = null): Nothing = errorMessage?.let {
  throw IllegalStateException(it)
} ?: throw IllegalStateException()

