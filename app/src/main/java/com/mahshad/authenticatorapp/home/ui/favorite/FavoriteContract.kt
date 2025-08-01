package com.mahshad.authenticatorapp.home.ui.favorite

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle

interface FavoriteContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun showFavoriteArticles(favoriteArticle: List<FavoriteArticle>)
    }

    interface Presenter : BasePresenter<View> {
        fun getFavoriteArticles()
    }
}