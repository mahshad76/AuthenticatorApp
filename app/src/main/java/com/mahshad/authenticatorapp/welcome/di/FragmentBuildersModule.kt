package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgetPasswordFragment
import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
    @ContributesAndroidInjector
    abstract fun contributeSignUpFragment(): SignUpFragment
    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgetPasswordFragment
}