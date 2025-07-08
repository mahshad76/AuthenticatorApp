package com.mahshad.authenticatorapp.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object AppCompatActivityExtensions {
    fun AppCompatActivity.replaceFragment(id: Int, fragment: Fragment) =
        supportFragmentManager.beginTransaction().replace(id, fragment)
}