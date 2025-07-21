package com.mahshad.authenticatorapp.welcome.data.localdatasource

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.disposables.Disposable

/**
 * Interface for interacting with user-specific data stored in shared preferences.
 * This interface leverages RxJava's reactive types [Maybe] and [Completable]
 * to represent asynchronous operations, ensuring non-blocking I/O and
 * clear handling of success, error, and completion states.
 */
interface UserSharedPref {
    fun readUsername(): Maybe<String?>
    fun readPassword(): Maybe<String?>
    fun saveUsername(username: String): Disposable
    fun savePassword(password: String): Disposable
}