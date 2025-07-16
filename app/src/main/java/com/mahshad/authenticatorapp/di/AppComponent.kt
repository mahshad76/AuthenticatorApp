package com.mahshad.authenticatorapp.di

import android.content.Context
import com.mahshad.authenticatorapp.welcome.di.PresenterModule
import com.mahshad.authenticatorapp.welcome.ui.WelcomeActivity
import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ThreadingModule::class,
        PresenterModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    /**
     * Provides a builder for the [WelcomeActivityComponent] to be created
     * as a subcomponent of [AppComponent].
     */
    //fun welcomeComponent(): WelcomeActivityComponent.Factory
    fun inject(activity: WelcomeActivity)
    fun inject(fragment: LoginFragment)
}