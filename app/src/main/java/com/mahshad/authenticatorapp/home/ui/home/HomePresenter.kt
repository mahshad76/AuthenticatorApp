package com.mahshad.authenticatorapp.home.ui.home

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.model.remote.ServerResponse
import com.mahshad.authenticatorapp.home.network.home.ApiService
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomePresenter @Inject constructor(
    private val apiService: ApiService,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : HomeContract.Presenter {
    private var view: HomeContract.View? = null

    override fun getArticles(): Disposable {
        view?.showLoading()
        return apiService.getRecentArticles("apple")
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe({ response: Response<ServerResponse?> ->
                view?.hideLoading()
                if (response.isSuccessful) Log.d("TAG", "getArticlesIsSuccessful: ${response.body()}")
                else view?.showErrorMessage(response.errorBody().toString())
            }, { error: Throwable -> Log.e("TAG", "getArticlesError: ${error.message}") })
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