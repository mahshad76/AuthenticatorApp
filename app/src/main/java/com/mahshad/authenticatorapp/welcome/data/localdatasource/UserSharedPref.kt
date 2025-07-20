package com.mahshad.authenticatorapp.welcome.data.localdatasource

import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * Interface for interacting with user-specific data stored in shared preferences.
 * This interface leverages RxJava's reactive types [Maybe] and [Completable]
 * to represent asynchronous operations, ensuring non-blocking I/O and
 * clear handling of success, error, and completion states.
 */
interface UserSharedPref {

    /**
     * Reads the stored username from shared preferences asynchronously.
     *
     * @return A [Maybe] that:
     * - Emits the username [String] if successfully read and a username exists, then completes.
     * - Completes without emitting any item if no username is found or it's an empty string.
     * - Emits an [Throwable] if an error occurs during the read operation.
     */
    fun readUsername(): Maybe<String?>

    /**
     * Reads the stored password from shared preferences asynchronously.
     *
     * @return A [Maybe] that:
     * - Emits the password [String] if successfully read and a password exists, then completes.
     * - Completes without emitting any item if no password is found or it's an empty string.
     * - Emits an [Throwable] if an error occurs during the read operation.
     */
    fun readPassword(): Maybe<String?>

    /**
     * Saves the provided username to shared preferences asynchronously.
     *
     * This operation is typically performed on a background thread.
     *
     * @param username The [String] username to be saved. A null or empty string
     * might result in the username being cleared, depending on
     * the implementation's logic.
     * @return A [Completable] that:
     * - Completes successfully when the username has been saved.
     * - Emits an [Throwable] if an error occurs during the save operation.
     */
    fun saveUsername(username: String): Completable

    /**
     * Saves the provided password to shared preferences asynchronously.
     *
     * This operation is typically performed on a background thread.
     *
     * @param password The [String] password to be saved. A null or empty string
     * might result in the password being cleared, depending on
     * the implementation's logic.
     * @return A [Completable] that:
     * - Completes successfully when the password has been saved.
     * - Emits an [Throwable] if an error occurs during the save operation.
     */
    fun savePassword(password: String): Completable
}