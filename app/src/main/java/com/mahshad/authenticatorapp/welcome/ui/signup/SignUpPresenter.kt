package com.mahshad.authenticatorapp.welcome.ui.signup

import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.welcome.data.localdatasource.UserSharedPref
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpPresenter @Inject constructor(
    private val userSharedPref: UserSharedPref,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : SignUpContract.SignUpPresenter {

    private var view: SignUpContract.SignUpView? = null

    override fun signUpValidationFlow(
        usernameObservable: Observable<CharSequence>,
        fullNameObservable: Observable<CharSequence>,
        passwordObservable: Observable<CharSequence>,
        phoneObservable: Observable<CharSequence>
    ) {
        TODO("Not yet implemented")
    }

    override fun attachView(view: SignUpContract.SignUpView) {
        this.view = view
    }

    override fun detachView() {
        TODO("Not yet implemented")
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }
}