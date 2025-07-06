package com.mahshad.authenticatorapp.di

import dagger.Component

@Component
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun factory(): AppComponent
    }
}