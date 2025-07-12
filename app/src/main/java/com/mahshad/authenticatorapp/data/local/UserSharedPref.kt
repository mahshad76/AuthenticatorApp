package com.mahshad.authenticatorapp.data.local

import io.reactivex.Completable
import io.reactivex.Maybe

interface UserSharedPref {
    fun getUser(): Maybe<String>
    fun getPassword(): Maybe<String>
    fun insertUser(username: String): Completable
    fun deleteUser(): Completable
}