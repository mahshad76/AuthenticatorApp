package com.mahshad.authenticatorapp.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object AppCompatActivityExtensions {
    fun AppCompatActivity.replaceFragment(id: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id, fragment).commit()
        transaction.addToBackStack(null)
    }
}