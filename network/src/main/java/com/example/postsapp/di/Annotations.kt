package com.example.postsapp.di

import javax.inject.Qualifier

object Annotations {

  @Qualifier
  @Retention(AnnotationRetention.BINARY)
  annotation class CoreNetwork
}