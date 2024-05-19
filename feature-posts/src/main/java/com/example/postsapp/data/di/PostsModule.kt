package com.example.postsapp.data.di

import com.example.postsapp.data.remote.PostsServiceApi
import com.example.postsapp.data.repository.LocalRepository
import com.example.postsapp.data.repository.LocalRepositoryImp
import com.example.postsapp.data.repository.RemoteRepository
import com.example.postsapp.data.repository.RemoteRepositoryImp
import com.example.postsapp.di.Annotations
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class PostsRepoModule {

    @Binds
    abstract fun bindRemoteRepository(repository: RemoteRepositoryImp): RemoteRepository

    @Binds
    abstract fun bindLocalRepository(repository: LocalRepositoryImp): LocalRepository

}

@Module
@InstallIn(SingletonComponent::class)
object PostsModule {
    @Provides
    @Singleton
    fun provideApiService(
        @Annotations.CoreNetwork
        retrofit: Retrofit
    ): PostsServiceApi = retrofit.create(
        PostsServiceApi::class.java
    )
}