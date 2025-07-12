package com.mahshad.authenticatorapp.di

import com.mahshad.authenticatorapp.data.local.UserSharedPref
import com.mahshad.authenticatorapp.data.local.UserSharedPrefImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun provideUsernameSharedPref(userSharedPref: UserSharedPrefImpl):
            UserSharedPref
}