package com.mahshad.authenticatorapp.welcome.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.DaggerFragmentExtensions.navigate
import com.mahshad.authenticatorapp.databinding.FragmentLoginBinding
import com.mahshad.authenticatorapp.home.HomeActivity
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import javax.inject.Inject

class LoginFragment : DaggerFragment(), Contract.View {

    private lateinit var loginFragment: FragmentLoginBinding
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupText: TextView
    private lateinit var forgetPassText: TextView
    private lateinit var usernameObservable: Observable<CharSequence>
    private lateinit var passwordObservable: Observable<CharSequence>
    private lateinit var loginButtonObservable: Observable<Unit>
    private lateinit var navController: NavController
    private lateinit var myContext: Context

    private val args: LoginFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }

    override fun onDestroyView() {
        presenter.detachView()
        presenter.destroyView()
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginFragment = FragmentLoginBinding.inflate(inflater)
        usernameText = loginFragment.usernameEditText
        passwordText = loginFragment.passwordEditText
        loginButton = loginFragment.myGradientMaterialButton
        signupText = loginFragment.registrationText
        signupText.setOnClickListener {
            navigate(R.id.navigate_from_login_to_signup)
        }
        forgetPassText = loginFragment.forgetPassword
        forgetPassText.setOnClickListener {
            navigate(R.id.navigate_from_login_to_forgotPass)
        }
        usernameObservable = usernameText.textChanges()
        passwordObservable = passwordText.textChanges()
        loginButtonObservable = loginButton.clicks()
        presenter.attachView(this)
        presenter.loginValidationFlow(usernameObservable, passwordObservable)
        presenter.loginCheck(loginButtonObservable)
        return loginFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameText.text.apply {
            clear()
            append(args.username)
        }
        passwordText.text.apply {
            clear()
            append(args.password)
        }
    }

    override fun setLoginButtonEnabled(isEnabled: Boolean) {
        Log.d("TAG", "setLoginButtonEnabled")
        loginButton.isEnabled = isEnabled
    }

    override fun showLoginError() = Toast.makeText(
        myContext, "Invalid Username or Password",
        Toast.LENGTH_SHORT
    ).show()

    override fun showLoginSuccess() {
        Toast.makeText(
            myContext, "Successful Login",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun getUsername() = usernameText.text.toString()

    override fun getPassword() = passwordText.text.toString()
}