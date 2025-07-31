package com.mahshad.authenticatorapp.home.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeFragmentDisposable

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeActivityDisposable
