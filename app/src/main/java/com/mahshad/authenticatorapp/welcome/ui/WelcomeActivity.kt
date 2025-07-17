package com.mahshad.authenticatorapp.welcome.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.AppCompatActivityExtensions.replaceFragment
import com.mahshad.authenticatorapp.databinding.ActivityMainBinding
import com.mahshad.authenticatorapp.welcome.ui.login.Contract
import com.mahshad.authenticatorapp.welcome.ui.login.LoginFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class WelcomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding: ActivityMainBinding

    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityHomeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, LoginFragment())
        }
    }
}