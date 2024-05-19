package com.example.postsapp.domain.workManger.di

import com.example.postsapp.domain.workManger.IWorkManger
import com.example.postsapp.domain.workManger.WorkMangerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkerModule {
    @Binds
    abstract fun bindWorker(repository: WorkMangerImp): IWorkManger
}