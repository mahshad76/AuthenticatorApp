package com.mahshad.authenticatorapp.welcome.ui.login

import android.widget.Button
import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface Contract {
    interface View : BaseView {
        fun usernameObservable(): Observable<CharSequence>?
        fun passwordObservable(): Observable<CharSequence>?
        fun loginButtonObservable(): Observable<Unit>?
        fun loginButton(): Button?
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
        fun processEditTextFlow(editTextObservable: Observable<CharSequence>?): Observable<String>?
        fun loginValidationFlow(
            usernameObservable: Observable<String>?,
            passwordObservable: Observable<String>?
        ): Disposable?

        fun loginButtonListener()
    }
}