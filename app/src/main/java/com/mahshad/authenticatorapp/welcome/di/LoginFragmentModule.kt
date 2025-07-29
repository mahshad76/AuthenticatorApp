package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.login.Contract
import com.mahshad.authenticatorapp.welcome.ui.login.Presenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class LoginFragmentModule {
    @Binds
    @LoginFragmentScope
    abstract fun providePresenter(presenter: Presenter): Contract.Presenter

    companion object {
        @Provides
        @LoginFragmentScope
        @LoginFragmentDisposable
        fun provideLoginFragmentDisposable(): CompositeDisposable = CompositeDisposable()
    }
}