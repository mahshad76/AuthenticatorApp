package com.mahshad.authenticatorapp.home.di.home

import com.mahshad.authenticatorapp.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityBuildersModule {
    @Scopes.HomeActivityScope
    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}