package com.mahshad.authenticatorapp.di

import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepository
import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideArticleRepository(articleRepositoryImpl: ArticleRepositoryImpl):
            ArticleRepository
}