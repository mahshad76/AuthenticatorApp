package com.mahshad.authenticatorapp.welcome.ui.login

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView

interface Contract {
    interface View : BaseView {
//        fun showLoading()
//        fun hideLoading()
//        fun successfulMessage()
//        fun unSuccessfulMessage()
    }

    /**
     * The logic for updating the [LoginFragment] is defined by the [Presenter].
     * Interactions with the views are converted into observables via the RxBinding library.
     */
    interface Presenter : BasePresenter<Contract.View> {


    }
}