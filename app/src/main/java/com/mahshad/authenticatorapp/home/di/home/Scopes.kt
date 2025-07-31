package com.mahshad.authenticatorapp.home.di.home

import javax.inject.Scope

class Scopes {
    @Scope
    @MustBeDocumented
    @Retention(value = AnnotationRetention.RUNTIME)
    annotation class HomeActivityScope

    @Scope
    @MustBeDocumented
    @Retention(value = AnnotationRetention.RUNTIME)
    annotation class HomeFragmentScope
}