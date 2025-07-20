package com.mahshad.authenticatorapp.welcome.data.localdatasource

import io.reactivex.Completable
import io.reactivex.Maybe

interface UserSharedPref {
    fun readUsername(): Maybe<String?>
    fun readPassword(): Maybe<String?>
    fun saveUsername(username: String): Completable
    fun savePassword(password: String): Completable
}