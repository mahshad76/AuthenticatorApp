package com.mahshad.authenticatorapp.home.ui.home

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepository
import com.mahshad.authenticatorapp.home.di.home.Scopes
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Scopes.HomeFragmentScope
class HomePresenter @Inject constructor(
    private val articleRepository: ArticleRepository,
    @MainScheduler private val mainScheduler: Scheduler,
    @IoScheduler private val ioScheduler: Scheduler
) : HomeContract.Presenter {
    private var view: HomeContract.View? = null

    override fun getArticles(searchQuery: Observable<CharSequence>): Disposable {
        view?.showLoading()
        return Flowable.combineLatest(
            articleRepository.getArticles(),
            searchQuery.toFlowable(BackpressureStrategy.LATEST)
        ) { articles: List<Article>, query: CharSequence ->
            if (query.toString() == "") {
                articles
            } else {
                articles.filter { article ->
                    article.title?.lowercase()?.contains(query.toString().lowercase()) ?: false
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
        likeIconObservable
            ?.throttleFirst(500, TimeUnit.MILLISECONDS)
            ?.observeOn(ioScheduler)
            ?.subscribe {
                val article = getArticle.invoke()
                articleRepository.updateLikedArticles(article)
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