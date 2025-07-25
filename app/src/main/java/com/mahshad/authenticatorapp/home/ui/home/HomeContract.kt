package com.mahshad.authenticatorapp.home.ui.home

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface HomeContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun showArticles(articles: List<Article>)
        fun showErrorMessage(error: String)
        fun showSuccessMessage()
    }

    /**
     *
     *  * The [Presenter] orchestrates the flow of data between the API services and the View.
     *  * It's responsible for:
     *  * - Invoking specific API endpoints to fetch raw data.
     *  * - Transforming the raw API responses into the data models
     *  * required by the Repository layer.
     *  * - Providing the prepared data to the [View] for display, handling presentation logic,
     *  * and managing UI state (e.g., loading, error states).
     *  */
    interface Presenter : BasePresenter<View> {
        fun getArticles(searchQuery: Observable<CharSequence>): Disposable
    }
}