package com.mahshad.authenticatorapp

import android.app.Application
import com.mahshad.authenticatorapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}