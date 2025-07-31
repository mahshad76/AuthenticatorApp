package com.mahshad.authenticatorapp.home.ui.home

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepository
import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.di.home.HomeFragmentDisposable
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Scopes.HomeFragmentScope
class HomePresenter @Inject constructor(
    private val articleRepository: ArticleRepository,
    @MainScheduler private val mainScheduler: Scheduler,
    @IoScheduler private val ioScheduler: Scheduler,
    @HomeFragmentDisposable private val compositeDisposable: CompositeDisposable
) : HomeContract.Presenter {
    private var view: HomeContract.View? = null

    override fun getArticles(searchQuery: Observable<CharSequence>) {
        view?.showLoading()
        val disposable = Flowable.combineLatest(
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
        compositeDisposable.add(disposable)
    }

    override fun updateLikedArticles(
        getArticle: () -> Article,
        likeIconObservable: Observable<Unit>?
    ) {
        val disposable = likeIconObservable
            ?.throttleFirst(500, TimeUnit.MILLISECONDS)
            ?.observeOn(ioScheduler)
            ?.subscribe {
                val article = getArticle.invoke()
                articleRepository.updateLikedArticles(article)
            }
        disposable?.let { compositeDisposable.add(it) }
    }

    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        compositeDisposable.clear()
    }
}