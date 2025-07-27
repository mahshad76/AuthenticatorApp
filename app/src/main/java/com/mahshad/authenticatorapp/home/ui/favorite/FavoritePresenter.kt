package com.mahshad.authenticatorapp.home.ui.favorite

import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.favorite.repository.FavoriteRepository
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritePresenter @Inject constructor(
    private val repository: FavoriteRepository,
    @MainScheduler private val mainScheduler: Scheduler
) :
    FavoriteContract.Presenter {
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
        TODO("Not yet implemented")
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }
}