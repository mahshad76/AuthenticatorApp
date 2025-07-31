package com.mahshad.authenticatorapp.home.di.home

import com.mahshad.authenticatorapp.home.di.HomeFragmentDisposable
import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.ui.home.HomeContract
import com.mahshad.authenticatorapp.home.ui.home.HomePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class HomePresenterModule {
    @Binds
    @Scopes.HomeFragmentScope
    abstract fun provideHomerPresenter(homePresenter: HomePresenter): HomeContract.Presenter

    companion object {
        @Provides
        @Scopes.HomeFragmentScope
        @HomeFragmentDisposable
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
//    @Binds
//    @Singleton
//    abstract fun provideFavoritePresenter(favoritePresenter: FavoritePresenter):
//            FavoriteContract.Presenter
}