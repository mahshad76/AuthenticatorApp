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
        getFullName: () -> String, // Not used in this snippet, but kept for context
        getPassword: () -> String,
        getPhone: () -> String     // Not used in this snippet, but kept for context
    ) {
        buttonObservable
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            // **Crucial: Switch to mainScheduler BEFORE accessing UI**
            .observeOn(mainScheduler)
            .map { // This block now executes on the main thread
                Pair(getUsername.invoke(), getPassword.invoke())
            }
            // **Now switch to ioScheduler for background work**
            .observeOn(ioScheduler)
            .switchMap { (username, password) ->
                Observable.zip(
                    userSharedPref.readUsername().toObservable(),
                    userSharedPref.readPassword().toObservable()
                ) { userPref, passwordPref ->
                    // This comparison happens on the IO scheduler
                    userPref == username && passwordPref == password
                }
            }
            // **Switch back to mainScheduler for UI updates**
            .observeOn(mainScheduler)
            .subscribe({ doesExist: Boolean ->
                if (doesExist) {
                    view?.unsuccessfulSignUp()
                } else {
                    view?.showSuccessfulSignup()
                }
            }, { error: Throwable ->
                // Good practice to log errors!
                Log.e("SignUpCheck", "Error during sign up check: ${error.message}", error)
                // Optionally, show an error message to the user
                // Assuming you have such a method
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