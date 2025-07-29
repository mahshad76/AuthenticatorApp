package com.mahshad.authenticatorapp.welcome.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginFragmentDisposable

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SignUpFragmentDisposable

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForgetPasswordFragmentDisposable