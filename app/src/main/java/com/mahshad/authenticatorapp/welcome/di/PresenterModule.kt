package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgotPassContract
import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgotPassPresenter
import com.mahshad.authenticatorapp.welcome.ui.login.Contract
import com.mahshad.authenticatorapp.welcome.ui.login.Presenter
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpContract
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpPresenter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PresenterModule {
    @Binds
    @Singleton
    abstract fun providePresenter(presenter: Presenter): Contract.Presenter

    @Binds
    @Singleton
    abstract fun provideSignUpPresenter(presenter: SignUpPresenter): SignUpContract.SignUpPresenter

    @Binds
    @Singleton
    abstract fun provideForgotPassPresenter(presenter: ForgotPassPresenter): ForgotPassContract.Presenter
}