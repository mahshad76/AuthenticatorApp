package com.mahshad.authenticatorapp.home.ui.home

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomePresenter @Inject constructor(
    private val articleRepository: ArticleRepository,
    @MainScheduler private val mainScheduler: Scheduler,
    @IoScheduler private val ioScheduler: Scheduler
) : HomeContract.Presenter {
    private var view: HomeContract.View? = null

    override fun getArticles(searchQuery: Observable<CharSequence>): Disposable {
        view?.showLoading()
        return Observable.combineLatest(
            articleRepository.getArticles().toObservable(),
            searchQuery
        ) { articles: List<Article>, query: CharSequence ->
            if (query.toString() == "") {
                articles
            } else {
                articles.filter { article ->
                    article.title?.contains(query.toString()) ?: false
                }
            }
        }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                {
                    view?.hideLoading()
                    view?.showArticles(it)
                },
                { error: Throwable ->
                    Log.e("TAG", "getArticlesError: ${error.message}")
                })
    }

    override fun updateLikedArticles(
        getArticle: () -> Article,
        likeIconObservable: Observable<Unit>?
    ) {
        likeIconObservable?.subscribe {
            Log.d(
                "TAG", "updateLikedArticles: likebutton is clicked with the title of " +
                        "${getArticle().title}"
            )
        }

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