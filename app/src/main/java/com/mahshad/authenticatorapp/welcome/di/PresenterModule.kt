package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.login.Contract
import com.mahshad.authenticatorapp.welcome.ui.login.Presenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {
    @Binds
    @WelcomeActivityScope
    abstract fun providePresenter(presenter: Presenter): Contract.Presenter
}