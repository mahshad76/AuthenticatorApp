package com.mahshad.authenticatorapp.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding3.material.itemSelections
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.AppCompatActivityExtensions.replaceFragment
import com.mahshad.authenticatorapp.databinding.ActivityHomeBinding
import com.mahshad.authenticatorapp.home.ui.home.HomeFragment
import io.reactivex.Observable

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navView: BottomNavigationView
    private lateinit var bottomNavigationObs: Observable<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navView = binding.navView
        bottomNavigationObs = navView.itemSelections()
        if (savedInstanceState == null) {
            replaceFragment(R.id.nav_host_fragment_activity_home2, HomeFragment())
        }
    }
}