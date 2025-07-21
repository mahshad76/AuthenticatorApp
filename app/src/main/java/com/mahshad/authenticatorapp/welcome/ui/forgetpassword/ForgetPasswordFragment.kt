package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mahshad.authenticatorapp.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment() {
    private lateinit var fragmentForgetPasswordBinding: FragmentForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentForgetPasswordBinding = FragmentForgetPasswordBinding.inflate(layoutInflater)
        return fragmentForgetPasswordBinding.root
    }
}