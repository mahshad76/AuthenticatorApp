package com.mahshad.authenticatorapp.home

import android.view.MenuItem
import com.mahshad.authenticatorapp.common.BasePresenter
import com.mahshad.authenticatorapp.common.BaseView
import io.reactivex.Observable

interface HomeActivityContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View> {
        fun replaceFragmentCall(bottomNavigationObs: Observable<MenuItem>)
    }
}