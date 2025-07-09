package com.mahshad.authenticatorapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ComputationScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScheduler