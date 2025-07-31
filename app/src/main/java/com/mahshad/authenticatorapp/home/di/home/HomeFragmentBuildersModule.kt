package com.mahshad.authenticatorapp.home.di.home

import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {
    @Scopes.HomeFragmentScope
    @ContributesAndroidInjector(modules = [HomePresenterModule::class])
    abstract fun contributeHomeFragment(): HomeFragment
}