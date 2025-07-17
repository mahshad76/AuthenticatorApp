package com.mahshad.authenticatorapp.welcome.data.localdatasource

interface UserSharedPref {
    fun readUsername(): String?
    fun readPassword(): String?
    fun saveUsername(username: String)
    fun savePassword(password: String)
}