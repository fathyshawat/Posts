package com.example.postsapp.resProvider


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

  @Binds
  abstract fun bindTextProvider(textProvider: ResourceProvider): IResourceProvider
}