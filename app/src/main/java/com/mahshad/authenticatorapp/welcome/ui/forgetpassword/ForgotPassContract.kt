package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable

interface ForgotPassContract {
    interface View : BaseView {
        fun setResetButtonEnabled(isEnabled: Boolean)
        fun successfulReset()
    }

    interface Presenter : BasePresenter<View> {
        fun enablingResetPassButton(
            passObservable: Observable<CharSequence>,
            confirmPassObservable: Observable<CharSequence>
        )

        fun reWritePassword(resetButtonObservable: Observable<Unit>, getNewPass: () -> String)
    }
}