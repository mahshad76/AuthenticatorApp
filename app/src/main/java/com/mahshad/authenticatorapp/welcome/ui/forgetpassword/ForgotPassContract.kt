package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface ForgotPassContract {
    interface View : BaseView {
        fun setResetButtonEnabled(isEnabled: Boolean)
        fun successfulReset()
    }

    interface Presenter : BasePresenter<View> {
        fun enablingResetPassButton(
            passObservable: Observable<CharSequence>,
            confirmPassObservable: Observable<CharSequence>
        ): Disposable

        fun reWritePassword(resetButtonObservable: Observable<Unit>, getNewPass: () -> String)
                : Disposable
    }
}