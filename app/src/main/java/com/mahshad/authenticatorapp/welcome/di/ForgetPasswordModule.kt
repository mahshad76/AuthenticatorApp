package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgotPassContract
import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgotPassPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class ForgetPasswordModule {
    @Binds
    @ForgetPasswordFragmentScope
    abstract fun provideForgotPassPresenter(presenter: ForgotPassPresenter):
            ForgotPassContract.Presenter

    companion object {
        @Provides
        @ForgetPasswordFragmentScope
        @ForgetPasswordFragmentDisposable
        fun provideForgetPasswordFragmentDisposable(): CompositeDisposable = CompositeDisposable()
    }
}