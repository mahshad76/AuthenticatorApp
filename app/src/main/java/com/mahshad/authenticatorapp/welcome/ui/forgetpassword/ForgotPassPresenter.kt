package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import android.util.Log
import com.mahshad.authenticatorapp.common.BasePresenterExtensions.processEditTextFlow
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.welcome.data.localdatasource.UserSharedPref
import com.mahshad.authenticatorapp.welcome.di.ForgetPasswordFragmentDisposable
import com.mahshad.authenticatorapp.welcome.di.ForgetPasswordFragmentScope
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ForgetPasswordFragmentScope
class ForgotPassPresenter @Inject constructor(
    private val userSharePref: UserSharedPref,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler,
    @ForgetPasswordFragmentDisposable private val compositeDisposable: CompositeDisposable
) : ForgotPassContract.Presenter {
    private var view: ForgotPassContract.View? = null

    override fun enablingResetPassButton(
        passObservable: Observable<CharSequence>,
        confirmPassObservable: Observable<CharSequence>
    ) {
        val disposable = Observable.combineLatest(
            processEditTextFlow(passObservable),
            processEditTextFlow(confirmPassObservable)
        ) { password: String, confirmation: String ->
            password.length > 7 && (confirmation == password)
        }
            .distinctUntilChanged()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe({ isEnabled ->
                view?.setResetButtonEnabled(isEnabled)
                Log.d("loginValidationFlow", "loginValidationFlow: ${isEnabled} ")
            }, { error: Throwable -> "loginValidationFlowError:${error}" }
            )
        compositeDisposable.add(disposable)
    }

    override fun reWritePassword(
        resetButtonObservable: Observable<Unit>,
        getNewPass: () -> String
    ) {
        val disposable = resetButtonObservable
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .map { getNewPass.invoke() }
            .doOnNext { newPass: String ->
                userSharePref.savePassword(newPass)
            }
            .observeOn(mainScheduler)
            .subscribe { newPass: String ->
                Log.d("TAG", "reWritePassword:${newPass}")
                view?.successfulReset()
            }
        compositeDisposable.add(disposable)
    }

    override fun attachView(view: ForgotPassContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        compositeDisposable.clear()
    }
}