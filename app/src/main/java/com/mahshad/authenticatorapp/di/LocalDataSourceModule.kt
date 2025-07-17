package com.mahshad.authenticatorapp.di

import android.content.Context
import android.content.SharedPreferences
import com.mahshad.authenticatorapp.data.local.UserSharedPref
import com.mahshad.authenticatorapp.data.local.UserSharedPrefImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun provideUsernameSharedPref(userSharedPref: UserSharedPrefImpl):
            UserSharedPref

    companion object {
        @Provides
        @Singleton
        fun userSharedPref(context: Context): SharedPreferences =
            context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    }
}