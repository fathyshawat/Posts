package com.example.postsapp.coroutines_scopes

import com.example.postsapp.coroutine_dispatchers.DefaultDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

  @DefaultScope
  @Singleton
  @Provides
  fun providesDefaultCoroutineScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

  @IoScope
  @Singleton
  @Provides
  fun providesIoCoroutineScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

  @MainScope
  @Singleton
  @Provides
  fun providesMainCoroutineScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
}