package com.mahshad.authenticatorapp.home.di

import com.mahshad.authenticatorapp.home.HomeActivity
import com.mahshad.authenticatorapp.home.di.home.HomeFragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityBuildersModule {
    @Scopes.HomeActivityScope
    @ContributesAndroidInjector(
        modules = [HomeActivityPresenterModule::class,
            HomeFragmentBuildersModule::class]
    )
    abstract fun contributeHomeActivity(): HomeActivity
}