package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView

interface Contract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
        fun signUpValidationFlow()
    }
}