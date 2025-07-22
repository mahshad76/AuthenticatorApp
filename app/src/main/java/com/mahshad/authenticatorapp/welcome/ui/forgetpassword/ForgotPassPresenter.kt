package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.welcome.data.localdatasource.UserSharedPref
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForgotPassPresenter @Inject constructor(
    private val userSharePref: UserSharedPref,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : ForgotPassContract.Presenter {
    private var view: ForgotPassContract.View? = null

    override fun enablingResetPassButton() {
        TODO("Not yet implemented")
    }

    override fun reWritePassword() {
        TODO("Not yet implemented")
    }

    override fun attachView(view: ForgotPassContract.View) {
        this.view = view
    }

    override fun detachView() {
        TODO("Not yet implemented")
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }
}