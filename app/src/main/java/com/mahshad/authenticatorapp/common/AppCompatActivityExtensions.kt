package com.mahshad.authenticatorapp.common

import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment

object DaggerFragmentExtensions {
    fun DaggerFragment.navigate(actionId: Int) {
        findNavController().navigate(actionId)
    }
}