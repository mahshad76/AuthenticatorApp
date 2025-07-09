package com.mahshad.authenticatorapp.welcome.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.databinding.FragmentLoginBinding
import io.reactivex.Observable

class LoginFragment : Fragment(), Contract.View {

    private var loginFragment: FragmentLoginBinding? = null
    private var usernameText: EditText? = null
    private var passwordText: EditText? = null
    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginFragment = FragmentLoginBinding.inflate(inflater)
        usernameText = loginFragment?.usernameEditText
        passwordText = loginFragment?.passwordEditText
        loginButton = loginFragment?.myGradientMaterialButton
        return loginFragment?.root
    }

    override fun observableUsernameEditText(): Observable<CharSequence>? {
        return usernameText?.textChanges()
    }

    override fun observablePasswordEditText(): Observable<CharSequence>? {
        return passwordText?.textChanges()
    }

    override fun observableLoginButton(): Observable<Unit>? {
        return loginButton?.clicks()
    }
}