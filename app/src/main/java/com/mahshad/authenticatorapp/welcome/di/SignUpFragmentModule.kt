package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpContract
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class SignUpFragmentModule {
    @Binds
    @SignUpFragmentScope
    abstract fun provideSignUpPresenter(presenter: SignUpPresenter): SignUpContract.SignUpPresenter
}