package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgetPasswordFragment
import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @SignUpFragmentScope
    @ContributesAndroidInjector(modules = [SignUpFragmentModule::class])
    abstract fun contributeSignUpFragment(): SignUpFragment

    @ForgetPasswordFragmentScope
    @ContributesAndroidInjector(modules = [ForgetPasswordModule::class])
    abstract fun contributeForgotPasswordFragment(): ForgetPasswordFragment

    @LoginFragmentScope
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    abstract fun contributeLoginFragment(): LoginFragment
}