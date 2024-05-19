package com.example.postsapp.coroutines_scopes

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainScope