package com.mahshad.authenticatorapp.signinsignup.di

import dagger.Subcomponent

@Subcomponent
interface WelcomeAppComponent {
    @Subcomponent.Factory
    interface Factory {
    fun create(): WelcomeAppComponent
}
    fun inject()
}