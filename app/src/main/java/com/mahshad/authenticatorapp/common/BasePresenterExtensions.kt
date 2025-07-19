package com.mahshad.authenticatorapp.common

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

object BasePresenterExtensions {
    fun <T : BaseView> BasePresenter<T>.processEditTextFlow(
        editTextObservable:
        Observable<CharSequence>
    ): Observable<String> {
        return editTextObservable.let {
            it.skip(1)
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.toString() }
        }
    }
}