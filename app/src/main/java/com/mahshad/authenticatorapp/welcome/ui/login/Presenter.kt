package com.mahshad.authenticatorapp.welcome.ui.login

import io.reactivex.Observable
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

    override fun processEditTextFlow(editTextObservable: Observable<CharSequence>?):
            Observable<String>? {
        return editTextObservable.let {
            it?.skip(1)
                ?.debounce(300, TimeUnit.MILLISECONDS)
                ?.map { it.toString() }
                ?.distinct()
                ?.observeOn(ioScheduler)
        }
    }

    override fun loginValidationFlow(
    ): Disposable? {
        return Observable.combineLatest(
            processEditTextFlow(view?.usernameEditText()),
            processEditTextFlow(view?.passwordEditText())
        ) { username: String, password: String ->
            isValidUsername(username) && isValidPassword(password)
        }
            .distinct()
            .observeOn(ioScheduler)
            .subscribeOn(mainScheduler)
            .subscribe({ isEnabled -> view?.loginButton()?.isEnabled = isEnabled }
            )
    }

    fun isValidUsername(username: String): Boolean =
        username.length > 3 && !username.isEmpty()

    fun isValidPassword(password: String): Boolean =
        password.isNotBlank() && password.length >= 6

    override fun loginButtonListener() {
        // view?.observableLoginButton()
    }
}