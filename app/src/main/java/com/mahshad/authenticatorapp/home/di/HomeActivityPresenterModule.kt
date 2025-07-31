package com.mahshad.authenticatorapp.home.di

import com.mahshad.authenticatorapp.home.HomeActivityContract
import com.mahshad.authenticatorapp.home.HomeActivityPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class HomeActivityPresenterModule {
    @Binds
    @Scopes.HomeActivityScope
    abstract fun provideHomeActivityPresenterModule(homeActivityPresenter: HomeActivityPresenter):
            HomeActivityContract.Presenter
}