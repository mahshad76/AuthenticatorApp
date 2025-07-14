package com.mahshad.authenticatorapp.welcome.di

import com.mahshad.authenticatorapp.welcome.ui.WelcomeActivity
import dagger.Subcomponent

@Subcomponent(modules = [WelcomeActivitySubcomponents::class])
@WelcomeActivityScope
interface WelcomeActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WelcomeActivityComponent
    }

    fun inject(welcomeActivity: WelcomeActivity)
}