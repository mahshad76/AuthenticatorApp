package com.mahshad.authenticatorapp.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment

object DaggerFragmentExtensions {
    fun DaggerFragment.navigate(actionId: Int) {
        findNavController().navigate(actionId)
    }
}
fun AppCompatActivity.replaceFragment(id: Int, fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(id, fragment).commit()
    transaction.addToBackStack(null)
}