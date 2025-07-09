package com.mahshad.authenticatorapp.welcome.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.mahshad.authenticatorapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), Contract.View {

    private lateinit var loginFragment: FragmentLoginBinding
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginFragment = FragmentLoginBinding.inflate(inflater)
        usernameText = loginFragment.usernameEditText
        passwordText = loginFragment.passwordEditText
        loginButton = loginFragment.myGradientMaterialButton
        return loginFragment.root ?: null
    }
}