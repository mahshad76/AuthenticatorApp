package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.databinding.FragmentForgetPasswordBinding
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import javax.inject.Inject

class ForgetPasswordFragment : DaggerFragment(), ForgotPassContract.View {
    private lateinit var fragmentForgetPasswordBinding: FragmentForgetPasswordBinding
    private lateinit var passwordText: EditText
    private lateinit var passwordTextObservable: Observable<CharSequence>
    private lateinit var confirmPassText: EditText
    private lateinit var confirmPasswordTextObservable: Observable<CharSequence>
    private lateinit var button: Button
    private lateinit var buttonObservable: Observable<Unit>
    private lateinit var navigateBack: TextView
    private lateinit var myContext: Context

    @Inject
    lateinit var presenter: ForgotPassContract.Presenter

    override fun onAttach(context: Context) {
        ////AndroidSupportInjection.inject(this)
        myContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentForgetPasswordBinding = FragmentForgetPasswordBinding.inflate(layoutInflater)
        passwordText = fragmentForgetPasswordBinding.forgotPassEditText
        passwordTextObservable = passwordText.textChanges()
        confirmPassText = fragmentForgetPasswordBinding.confirmPassText
        confirmPasswordTextObservable = confirmPassText.textChanges()
        button = fragmentForgetPasswordBinding.myGradientMaterialButton
        buttonObservable = button.clicks()
        navigateBack = fragmentForgetPasswordBinding.signIn
        navigateBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        presenter.attachView(this)
        presenter.enablingResetPassButton(passwordTextObservable, confirmPasswordTextObservable)
        presenter.reWritePassword(buttonObservable, { passwordText.text.toString() })
        return fragmentForgetPasswordBinding.root
    }

    override fun setResetButtonEnabled(isEnabled: Boolean) {
        button.isEnabled = isEnabled
    }

    override fun successfulReset() = Toast.makeText(
        myContext, "The password is reset successfully",
        Toast.LENGTH_SHORT
    ).show()
}