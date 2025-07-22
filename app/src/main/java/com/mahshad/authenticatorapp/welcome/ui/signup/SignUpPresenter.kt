package com.mahshad.authenticatorapp.welcome.ui.signup

import android.util.Log
import com.mahshad.authenticatorapp.common.BasePresenterExtensions.processEditTextFlow
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.welcome.data.localdatasource.UserSharedPref
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
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
        Observable.combineLatest(
            processEditTextFlow(usernameObservable),
            processEditTextFlow(fullNameObservable),
            processEditTextFlow(passwordObservable),
            processEditTextFlow(phoneObservable)
        ) { username: String, fullName: String, password: String, phone: String ->
            username.length > 7 && fullName.length > 6 && password.length > 7 && phone.length == 10
        }.subscribeOn(ioScheduler).observeOn(mainScheduler).subscribe { isValid: Boolean ->
            view?.setSignUpButtonEnabled(isValid)
        }
    }

    override fun signUpCheck(
        buttonObservable: Observable<Unit>,
        getUsername: () -> String,
        getFullName: () -> String,
        getPassword: () -> String,
        getPhone: () -> String
    ) {
        buttonObservable
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .observeOn(mainScheduler)
            .map {
                Pair(getUsername.invoke(), getPassword.invoke())
            }
            .observeOn(ioScheduler)
            .switchMap { (username, password) ->
                Observable.zip(
                    userSharedPref.readUsername().toObservable(),
                    userSharedPref.readPassword().toObservable()
                ) { userPref, passwordPref ->
                    Triple(
                        (userPref == username) && (passwordPref == password),
                        username,
                        password
                    )
                }
            }
            .observeOn(mainScheduler)
            .subscribe({ (doesExist: Boolean, username: String, password: String) ->
                if (doesExist) {
                    view?.unsuccessfulSignUp()
                } else {
                    userSharedPref.saveUsername(username)
                    userSharedPref.savePassword(password)
                    view?.showSuccessfulSignup()
                }
            }, { error: Throwable ->
                Log.e("SignUpCheck", "Error during sign up check: ${error.message}", error)
            })
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