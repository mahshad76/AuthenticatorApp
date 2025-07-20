package com.mahshad.authenticatorapp.welcome.data.localdatasource

import io.reactivex.Completable
import io.reactivex.Maybe

class UserSharedPrefImpl : UserSharedPref {

    companion object {
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
    }

    override fun readUsername(): Maybe<String?> {
        TODO("Not yet implemented")
    }

    override fun readPassword(): Maybe<String?> {
        TODO("Not yet implemented")
    }

    override fun saveUsername(username: String): Completable {
        TODO("Not yet implemented")
    }

    override fun savePassword(password: String): Completable {
        TODO("Not yet implemented")
    }
}