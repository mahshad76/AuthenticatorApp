package com.mahshad.authenticatorapp.di

import com.mahshad.authenticatorapp.data.local.UsernameSharedPref
import com.mahshad.authenticatorapp.data.local.UsernameSharedPrefImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun provideUsernameSharedPref(usernameSharedPref: UsernameSharedPrefImpl):
            UsernameSharedPref
}