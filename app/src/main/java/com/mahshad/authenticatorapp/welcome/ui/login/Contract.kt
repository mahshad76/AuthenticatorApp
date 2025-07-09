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

    interface Presenter: BasePresenter<Contract.View> {


    }
}