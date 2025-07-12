package com.mahshad.authenticatorapp.welcome.ui.login

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Presenter @Inject constructor(
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) :
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
    ) {
        return Observable.combineLatest(
            usernameObservable,
            passwordObservable
        ) { username: String, password: String ->
            isValidUsername(username) && isValidPassword(password)
        }.observeOn(ioScheduler).subscribeOn(mainScheduler).subscribe(
            object : Observer<Boolean> {
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

                override fun onNext(t: Boolean) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun isValidUsername(username: String): Boolean =
        username.length > 3 && !username.isEmpty()

    fun isValidPassword(password: String): Boolean =
        password.isNotBlank() && password.length >= 6

    override fun loginButtonListener() {
        view?.observableLoginButton()
    }
}