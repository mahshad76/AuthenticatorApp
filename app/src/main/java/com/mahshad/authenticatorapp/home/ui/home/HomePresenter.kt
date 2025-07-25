package com.mahshad.authenticatorapp.home.ui.home

import android.util.Log
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomePresenter @Inject constructor(
    private val articleRepository: ArticleRepository,
    @MainScheduler private val mainScheduler: Scheduler
) : HomeContract.Presenter {
    private var view: HomeContract.View? = null

    override fun getArticles(): Disposable {
        view?.showLoading()
        return articleRepository
            .getArticles()
            .observeOn(mainScheduler)
            .subscribe(
                {
                    view?.hideLoading()
                    view?.showArticles(it)
                },
                { error: Throwable -> Log.e("TAG", "getArticlesError: ${error.message}") })
    }

    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        TODO("Not yet implemented")
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }
}