package com.mahshad.authenticatorapp.di

import android.content.Context
import com.mahshad.authenticatorapp.MyApplication
import com.mahshad.authenticatorapp.home.di.DatabaseModule
import com.mahshad.authenticatorapp.home.di.HomeActivityBuildersModule
import com.mahshad.authenticatorapp.home.di.HomeFragmentBuildersModule
import com.mahshad.authenticatorapp.home.di.HomePresenterModule
import com.mahshad.authenticatorapp.home.di.NetworkModule
import com.mahshad.authenticatorapp.welcome.data.localdatasource.UserSharedPrefModule
import com.mahshad.authenticatorapp.welcome.di.ActivityBuildersModule
import com.mahshad.authenticatorapp.welcome.di.FragmentBuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ThreadingModule::class,
        AndroidSupportInjectionModule::class,
        UserSharedPrefModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        //PresenterModule::class,
        NetworkModule::class,
        HomePresenterModule::class,
        HomeActivityBuildersModule::class,
        HomeFragmentBuildersModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: MyApplication)
}