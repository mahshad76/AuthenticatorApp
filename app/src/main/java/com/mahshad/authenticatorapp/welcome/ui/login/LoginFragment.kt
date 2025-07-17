package com.mahshad.authenticatorapp.welcome.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.databinding.FragmentLoginBinding
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import javax.inject.Inject

class LoginFragment : Fragment(), Contract.View {

    private var loginFragment: FragmentLoginBinding? = null
    private var usernameText: EditText? = null
    private var passwordText: EditText? = null
    private var loginButton: Button? = null
    private var usernameObservable: Observable<CharSequence>? = null
    private var passwordObservable: Observable<CharSequence>? = null
    private var loginButtonObservable: Observable<Unit>? = null
    private lateinit var myContext: Context

    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginFragment = FragmentLoginBinding.inflate(inflater)
        usernameText = loginFragment?.usernameEditText
        passwordText = loginFragment?.passwordEditText
        loginButton = loginFragment?.myGradientMaterialButton
        usernameObservable = usernameText?.textChanges()
        passwordObservable = passwordText?.textChanges()
        presenter.attachView(this)
        presenter.loginValidationFlow(usernameObservable, passwordObservable)
        return loginFragment?.root
    }

    override fun setLoginButtonEnabled(isEnabled: Boolean) {
        Log.d("TAG", "setLoginButtonEnabled")
        loginButton?.isEnabled = isEnabled
    }

    override fun showLoginError() = Toast.makeText(
        myContext, "Invalid Username or Password",
        Toast.LENGTH_SHORT
    ).show()
}