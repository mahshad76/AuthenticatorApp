package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpContract
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class SignUpFragmentModule {
    @Binds
    @SignUpFragmentScope
    abstract fun provideSignUpPresenter(presenter: SignUpPresenter): SignUpContract.SignUpPresenter

    companion object {
        @Provides
        @SignUpFragmentScope
        @SignUpFragmentDisposable
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}