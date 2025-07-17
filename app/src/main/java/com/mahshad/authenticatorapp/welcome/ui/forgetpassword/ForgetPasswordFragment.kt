package com.mahshad.authenticatorapp.welcome.ui.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.mahshad.authenticatorapp.databinding.FragmentSignUpBinding
import io.reactivex.Observable

class ForgetPasswordFragment : Fragment() {
    private lateinit var signUpFragment: FragmentSignUpBinding
    private lateinit var usernameEditText: EditText
    private lateinit var usernameEditTextObservable: Observable<CharSequence>
    private lateinit var fullNameEditText: EditText
    private lateinit var fullNameEditTextObservable: Observable<CharSequence>
    private lateinit var passwordEditText: EditText
    private lateinit var passwordEditTextObservable: Observable<CharSequence>
    private lateinit var phoneEditText: EditText
    private lateinit var phoneEditTextObservable: Observable<CharSequence>
    private lateinit var signUpButton: Button
    private lateinit var signUpButtonObservable: Observable<Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signUpFragment = FragmentSignUpBinding.inflate(inflater)
        usernameEditText = signUpFragment.usernameEditText
        usernameEditTextObservable = usernameEditText.textChanges()
        fullNameEditText = signUpFragment.fullNameEditText
        fullNameEditTextObservable = fullNameEditText.textChanges()
        passwordEditText = signUpFragment.passwordEditText
        passwordEditTextObservable = passwordEditText.textChanges()
        phoneEditText = signUpFragment.phoneEditText
        phoneEditTextObservable = phoneEditText.textChanges()
        signUpButton = signUpFragment.myGradientMaterialButton
        signUpButtonObservable = signUpButton.clicks()
        return signUpFragment.root
    }
}