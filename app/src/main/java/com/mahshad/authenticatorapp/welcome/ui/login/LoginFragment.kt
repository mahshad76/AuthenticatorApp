package com.mahshad.authenticatorapp.welcome.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.AppCompatActivityExtensions.replaceFragment
import com.mahshad.authenticatorapp.databinding.FragmentLoginBinding
import com.mahshad.authenticatorapp.welcome.ui.forgetpassword.ForgetPasswordFragment
import com.mahshad.authenticatorapp.welcome.ui.signup.SignUpFragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import javax.inject.Inject

class LoginFragment : Fragment(), Contract.View {

    private lateinit var loginFragment: FragmentLoginBinding
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupText: TextView
    private lateinit var forgetPassText: TextView
    private lateinit var usernameObservable: Observable<CharSequence>
    private lateinit var passwordObservable: Observable<CharSequence>
    private lateinit var loginButtonObservable: Observable<Unit>
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
        usernameText = loginFragment.usernameEditText
        passwordText = loginFragment.passwordEditText
        loginButton = loginFragment.myGradientMaterialButton
        signupText = loginFragment.registrationText
        signupText.setOnClickListener {
            (activity as AppCompatActivity).replaceFragment(
                R.id.fragment_container, SignUpFragment()
            )
        }
        forgetPassText = loginFragment.forgetPassword
        forgetPassText?.setOnClickListener {
            (activity as AppCompatActivity).replaceFragment(
                R.id.fragment_container, ForgetPasswordFragment()
            )
        }
        usernameObservable = usernameText.textChanges()
        passwordObservable = passwordText.textChanges()
        loginButtonObservable = loginButton.clicks()
        presenter.attachView(this)
        presenter.loginValidationFlow(usernameObservable, passwordObservable)
        presenter.loginCheck(loginButtonObservable)
        return loginFragment.root
    }

    override fun setLoginButtonEnabled(isEnabled: Boolean) {
        Log.d("TAG", "setLoginButtonEnabled")
        loginButton.isEnabled = isEnabled
    }

    override fun showLoginError() = Toast.makeText(
        myContext, "Invalid Username or Password",
        Toast.LENGTH_SHORT
    ).show()

    override fun getUsername() = usernameText.text.toString()

    override fun getPassword() = passwordText.text.toString()
}