package com.mahshad.authenticatorapp.welcome.data.localdatasource

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UserSharedPrefModule {
    @Binds
    @Singleton
    abstract fun provideUserSharedPref(userSharedPrefImpl: UserSharedPrefImpl): UserSharedPref
}