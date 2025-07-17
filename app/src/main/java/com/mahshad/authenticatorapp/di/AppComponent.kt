package com.mahshad.authenticatorapp.di

import android.content.Context
import com.mahshad.authenticatorapp.MyApplication
import com.mahshad.authenticatorapp.welcome.di.ActivityBuildersModule
import com.mahshad.authenticatorapp.welcome.di.FragmentBuildersModule
import com.mahshad.authenticatorapp.welcome.di.PresenterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ThreadingModule::class,
        AndroidSupportInjectionModule::class,
        LocalDataSourceModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        PresenterModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: MyApplication)
}