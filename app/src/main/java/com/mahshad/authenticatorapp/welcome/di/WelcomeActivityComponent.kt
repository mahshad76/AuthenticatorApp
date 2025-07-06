package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.WelcomeActivity
import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgetPasswordFragment
import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpFragment
import dagger.Subcomponent

@Subcomponent
interface WelcomeActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WelcomeActivityComponent
    }

    fun inject(welcomeActivity: WelcomeActivity)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(forgetPasswordFragment: ForgetPasswordFragment)
}