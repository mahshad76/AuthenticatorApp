package com.mahshad.authenticatorapp.home.di

import com.mahshad.authenticatorapp.home.HomeActivity
import dagger.Subcomponent

@Subcomponent
interface HomeActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeActivityComponent
    }

    fun inject(homeActivity: HomeActivity)
}