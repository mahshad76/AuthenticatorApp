package com.mahshad.authenticatorapp.welcome.ui.login

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable

interface Contract {
    interface View : BaseView {
        fun observableUsernameEditText(): Observable<CharSequence>?
        fun observablePasswordEditText(): Observable<CharSequence>?
        fun observableLoginButton(): Observable<Unit>?
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
        fun editTextToObservable(): Observable<String>?
        fun loginValidationFlow(
            usernameObservable: Observable<String>?,
            passwordObservable: Observable<String>?
        )

        fun loginButtonListener()
    }
}