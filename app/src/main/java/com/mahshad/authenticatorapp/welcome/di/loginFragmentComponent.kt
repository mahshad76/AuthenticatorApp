package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import dagger.Subcomponent

@Subcomponent
@loginFragmentScope
interface loginFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): loginFragmentComponent
    }

    fun inject(loginFragment: LoginFragment)
}