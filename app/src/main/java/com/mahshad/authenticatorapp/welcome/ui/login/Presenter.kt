package com.mahshad.authenticatorapp.welcome.ui.login

import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Presenter @Inject constructor(private val ioScheduler: Scheduler) :
    Contract.Presenter {
    private var view: Contract.View? = null

    override fun attachView(view: Contract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }

    override fun editTextToObservable(): Observable<String>? {
        return view?.let { nonNullEditTextView ->
            nonNullEditTextView
                .observableUsernameEditText()
                ?.skip(1)
                ?.debounce(300, TimeUnit.MILLISECONDS)
                ?.map { it.toString() }
                ?.distinct()
                ?.observeOn(ioScheduler)
        }
    }


    override fun loginValidationFlow(
        usernameObservable: Observable<String>?,
        passwordObservable: Observable<String>?
    ): Observable<Boolean> {
        return Observable.combineLatest(
            usernameObservable,
            passwordObservable
        ) { username: String, password: String ->
            isValidUsername(username) && isValidPassword(password)
        }
    }

    fun isValidUsername(username: String): Boolean {
        return false
    }

    fun isValidPassword(password: String): Boolean {
        return false
    }

    override fun loginButtonListener() {
        view?.observableLoginButton()
    }
}