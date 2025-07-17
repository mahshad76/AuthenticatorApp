package com.mahshad.authenticatorapp.welcome.ui.signup

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable

interface SignUpContract {
    interface SignUpView : BaseView {
        fun showSuccessfulSignup()
        fun unsuccessfulSignUp()
    }

    interface SignUpPresenter : BasePresenter<SignUpView> {
        fun signUpValidationFlow(
            usernameObservable: Observable<CharSequence>,
            fullNameObservable: Observable<CharSequence>,
            passwordObservable: Observable<CharSequence>,
            phoneObservable: Observable<CharSequence>
        )
    }
}