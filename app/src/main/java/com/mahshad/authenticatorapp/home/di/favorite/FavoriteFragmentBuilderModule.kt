package com.mahshad.authenticatorapp.home.di.favorite

import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.ui.favorite.FavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuilderModule {
    @Scopes.FavoriteFragmentScope
    @ContributesAndroidInjector(modules = [FavoritePresenterModule::class])
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}