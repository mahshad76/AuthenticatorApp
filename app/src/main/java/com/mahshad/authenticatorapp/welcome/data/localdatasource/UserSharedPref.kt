package com.mahshad.authenticatorapp.welcome.data.localdatasource

interface UserSharedPref {
    fun readUsername()
    fun readPassword()
    fun saveUsername()
    fun savePassword()
}