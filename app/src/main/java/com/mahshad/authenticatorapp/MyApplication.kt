package com.mahshad.authenticatorapp

import android.app.Application
import com.mahshad.authenticatorapp.di.AppComponent
import com.mahshad.authenticatorapp.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

}