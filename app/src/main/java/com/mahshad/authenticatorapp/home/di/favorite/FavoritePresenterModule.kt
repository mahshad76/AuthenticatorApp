package com.mahshad.authenticatorapp.home.di.favorite

import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.ui.favorite.FavoriteContract
import com.mahshad.authenticatorapp.home.ui.favorite.FavoritePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class FavoritePresenterModule {
    @Binds
    @Scopes.FavoriteFragmentScope
    abstract fun provideFavoritePresenter(favoritePresenter: FavoritePresenter):
            FavoriteContract.Presenter
}