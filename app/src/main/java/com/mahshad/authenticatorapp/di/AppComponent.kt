package com.mahshad.authenticatorapp.di

import android.content.Context
import com.mahshad.authenticatorapp.welcome.di.WelcomeActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun factory(@BindsInstance context: Context): AppComponent
    }
    /*
     * Provides a builder for the [WelcomeActivityComponent] to be created
     * as a subcomponent of [AppComponent].
     */

    fun welcomeComponent(): WelcomeActivityComponent.Factory
}