package com.mahshad.authenticatorapp.data.local

import io.reactivex.Completable
import io.reactivex.Maybe

interface UsernameSharedPref {
    fun insertUser(): Completable
    fun deleteUser(): Completable
    fun getUser(): Maybe<String>
}