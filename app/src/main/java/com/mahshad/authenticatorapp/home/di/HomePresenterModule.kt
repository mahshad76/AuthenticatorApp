package com.mahshad.authenticatorapp.home.di

import com.mahshad.authenticatorapp.home.di.home.Scopes
import com.mahshad.authenticatorapp.home.ui.home.HomeContract
import com.mahshad.authenticatorapp.home.ui.home.HomePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class HomePresenterModule {
    @Binds
    @Scopes.HomeFragmentScope
    abstract fun provideHomerPresenter(homePresenter: HomePresenter):
            HomeContract.Presenter

//    @Binds
//    @Singleton
//    abstract fun provideFavoritePresenter(favoritePresenter: FavoritePresenter):
//            FavoriteContract.Presenter
}