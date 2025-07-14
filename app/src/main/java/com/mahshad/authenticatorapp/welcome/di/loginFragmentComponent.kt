package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresenterModule::class])
@LoginFragmentScope
interface LoginFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginFragmentComponent
    }

    fun inject(loginFragment: LoginFragment)
}