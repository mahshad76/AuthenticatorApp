package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.login.Contract
import com.mahshad.authenticatorapp.welcome.ui.login.Presenter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PresenterModule {
    @Binds
    @Singleton
    abstract fun providePresenter(presenter: Presenter): Contract.Presenter
}