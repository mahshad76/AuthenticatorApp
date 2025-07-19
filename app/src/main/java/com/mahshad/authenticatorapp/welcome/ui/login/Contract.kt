package com.mahshad.authenticatorapp.welcome.ui.login

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface Contract {
    interface View : BaseView {
        fun setLoginButtonEnabled(isEnabled: Boolean)
        fun showLoginError()
        fun getUsername(): String?
        fun getPassword(): String?


    }

    /**
     * The logic for updating the [LoginFragment] is defined by the [Presenter].
     * Interactions with the views are converted into observables via the RxBinding library.
     */
    interface Presenter : BasePresenter<Contract.View> {
        fun loginValidationFlow(
            usernameObservable: Observable<CharSequence>,
            passwordObservable: Observable<CharSequence>
        ): Disposable?

        fun loginCheck(buttonObservable: Observable<Unit>)
    }
}