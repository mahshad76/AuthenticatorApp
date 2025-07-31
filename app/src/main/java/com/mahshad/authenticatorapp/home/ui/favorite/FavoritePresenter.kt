package com.mahshad.authenticatorapp.home.ui.favorite

import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.favorite.repository.FavoriteRepository
import com.mahshad.authenticatorapp.home.di.favorite.Scopes
import io.reactivex.Scheduler
import javax.inject.Inject

@Scopes.FavoriteFragmentScope
class FavoritePresenter @Inject constructor(
    private val repository: FavoriteRepository,
    @MainScheduler private val mainScheduler: Scheduler
) : FavoriteContract.Presenter {
    var view: FavoriteContract.View? = null
    override fun getFavoriteArticles() {
        ///TODO(calling the function in the view to shows the favorite articles)
        repository.getFavoriteArticles()
            .observeOn(mainScheduler)
            .subscribe({ }, { })
    }

    override fun attachView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }
}