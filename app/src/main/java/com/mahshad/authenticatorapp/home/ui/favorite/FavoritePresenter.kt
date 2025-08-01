package com.mahshad.authenticatorapp.home.ui.favorite

import android.util.Log
import com.mahshad.authenticatorapp.home.data.favorite.repository.FavoriteRepository
import com.mahshad.authenticatorapp.home.di.HomeActivityDisposable
import com.mahshad.authenticatorapp.home.di.Scopes
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@Scopes.FavoriteFragmentScope
class FavoritePresenter @Inject constructor(
    private val repository: FavoriteRepository,
    @HomeActivityDisposable private val compositeDisposable: CompositeDisposable
) : FavoriteContract.Presenter {
    var view: FavoriteContract.View? = null
    override fun getFavoriteArticles() {
        view?.showLoading()
        val disposable = repository.getFavoriteArticles()
            .subscribe(
                { favoriteArticles ->
                    view?.hideLoading()
                    view?.showFavoriteArticles(favoriteArticles)
                },
                { error: Throwable ->
                    view?.hideLoading()
                    Log.e("TAG", "getFavoriteArticlesError: ${error.message}")
                })
        compositeDisposable.add(disposable)
    }

    override fun attachView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        compositeDisposable.clear()
    }
}