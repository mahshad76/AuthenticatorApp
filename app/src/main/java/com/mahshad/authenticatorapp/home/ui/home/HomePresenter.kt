package com.mahshad.authenticatorapp.home.ui.home

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.model.remote.ServerResponseDTO
import com.mahshad.authenticatorapp.home.data.home.repository.ArticleRepository
import com.mahshad.authenticatorapp.home.network.home.ApiService
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomePresenter @Inject constructor(
    private val articleRepository: ArticleRepository,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : HomeContract.Presenter {
    private var view: HomeContract.View? = null

//    override fun getArticles(): Disposable {
//        view?.showLoading()
//        return apiService.getRecentArticles("apple")
//            .subscribeOn(ioScheduler)
//            .observeOn(mainScheduler)
//            .subscribe({ response: Response<ServerResponseDTO?> ->
//                view?.hideLoading()
//                if (response.isSuccessful) view?.showArticles(response.body().toString())
//                else view?.showErrorMessage(response.errorBody().toString())
//            }, { error: Throwable -> Log.e("TAG", "getArticlesError: ${error.message}") })
//    }

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