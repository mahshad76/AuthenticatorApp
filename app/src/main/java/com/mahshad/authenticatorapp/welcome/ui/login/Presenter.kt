package com.mahshad.authenticatorapp.welcome.ui.login

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Presenter @Inject constructor(
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
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
                ?.observeOn(ioScheduler)
        }
    }

    override fun loginValidationFlow(
        usernameObservable: Observable<CharSequence>?,
        passwordObservable: Observable<CharSequence>?
    ): Disposable? {
        return Observable.combineLatest(
            processEditTextFlow(usernameObservable),
            processEditTextFlow(passwordObservable)
        ) { username: String, password: String ->
            isValidUsername(username) && isValidPassword(password)
        }
            .distinctUntilChanged()
            .observeOn(ioScheduler)
            .subscribeOn(mainScheduler)
            .subscribe({ isEnabled ->
                view?.setLoginButtonEnabled(isEnabled)
                Log.d("loginValidationFlow", "loginValidationFlow: ${isEnabled} ")
            }, {error: Throwable->"loginValidationFlowError:${error}"}
            )
    }

    fun isValidUsername(username: CharSequence): Boolean =
        username.length > 3 && !username.isEmpty()

    fun isValidPassword(password: CharSequence): Boolean =
        password.isNotBlank() && password.length >= 6
}