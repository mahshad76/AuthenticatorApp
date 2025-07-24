package com.mahshad.authenticatorapp.home.di

import com.mahshad.authenticatorapp.home.ui.home.HomeContract
import com.mahshad.authenticatorapp.home.ui.home.HomePresenter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class HomePresenterModule {
    @Binds
    @Singleton
    abstract fun provideHomerPresenter(homePresenter: HomePresenter): HomeContract.Presenter
}