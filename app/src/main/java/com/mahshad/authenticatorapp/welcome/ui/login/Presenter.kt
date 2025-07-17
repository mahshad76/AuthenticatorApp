package com.mahshad.authenticatorapp.welcome.ui.login

import android.util.Log
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.welcome.data.localdatasource.UserSharedPref
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Presenter @Inject constructor(
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler,
    private val userSharedPref: UserSharedPref
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

    override fun processEditTextFlow(editTextObservable: Observable<CharSequence>):
            Observable<String> {
        return editTextObservable.let {
            it.skip(1)
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.toString() }
        }
    }

    //you need to add the subscriptions to the disposable array to clear them as the view is destroyed.

    override fun loginValidationFlow(
        usernameObservable: Observable<CharSequence>,
        passwordObservable: Observable<CharSequence>
    ): Disposable? {
        return Observable.combineLatest(
            processEditTextFlow(usernameObservable),
            processEditTextFlow(passwordObservable)
        ) { username: String, password: String ->
            isValidUsername(username) && isValidPassword(password)
        }
            .distinctUntilChanged()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe({ isEnabled ->
                view?.setLoginButtonEnabled(isEnabled)
                Log.d("loginValidationFlow", "loginValidationFlow: ${isEnabled} ")
            }, { error: Throwable -> "loginValidationFlowError:${error}" }
            )
    }

    override fun loginCheck(buttonObservable: Observable<Unit>) {
        buttonObservable
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .observeOn(ioScheduler)
            .map {
                val username = view?.getUsername() ?: ""
                val password = view?.getPassword() ?: ""
                userSharedPref.readPassword() == username &&
                        userSharedPref.readPassword() == password
            }
            .filter { !it }
            .observeOn(mainScheduler)
            .subscribe(
                { view?.showLoginError() },
                { error: Throwable ->
                    Log.e("loginCheckError", "loginCheck: ${error.message}", error)
                }
            )
    }

    fun isValidUsername(username: String): Boolean =
        !username.isEmpty() && username.length > 7

    fun isValidPassword(password: String): Boolean =
        !password.isEmpty() && password.length > 7
}