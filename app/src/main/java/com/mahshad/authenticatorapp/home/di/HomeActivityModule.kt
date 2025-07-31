package com.mahshad.authenticatorapp.home.di

import com.mahshad.authenticatorapp.home.HomeActivityContract
import com.mahshad.authenticatorapp.home.HomeActivityPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class HomeActivityModule {
    @Binds
    @Scopes.HomeActivityScope
    abstract fun provideHomeActivityPresenterModule(homeActivityPresenter: HomeActivityPresenter):
            HomeActivityContract.Presenter

    companion object {
        @Provides
        @Scopes.HomeActivityScope
        @HomeActivityDisposable
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}