package com.mahshad.authenticatorapp.welcome.data.localdatasource

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSharedPrefImpl @Inject constructor(private val userSharedPref: SharedPreferences) :
    UserSharedPref {

    companion object {
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
    }

    override fun readUsername() = userSharedPref.getString(USERNAME_KEY, null)

    override fun readPassword() = userSharedPref.getString(PASSWORD_KEY, null)

    override fun saveUsername(username: String) = userSharedPref.edit {
        putString(USERNAME_KEY, username).apply()
    }

    override fun savePassword(password: String) = userSharedPref.edit {
        putString(PASSWORD_KEY, password).apply()
    }
}