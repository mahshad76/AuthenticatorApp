package com.mahshad.authenticatorapp.home

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding3.material.itemSelections
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.replaceFragment
import com.mahshad.authenticatorapp.databinding.ActivityHomeBinding
import com.mahshad.authenticatorapp.home.ui.home.HomeFragment
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HomeActivityContract.View {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navView: BottomNavigationView
    private lateinit var bottomNavigationObs: Observable<MenuItem>

    @Inject
    lateinit var presenter: HomeActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navView = binding.navView
        bottomNavigationObs = navView.itemSelections()

        if (savedInstanceState == null) {
            replaceFragment(R.id.nav_host_fragment_activity_home2, HomeFragment())
            presenter.replaceFragmentCall(bottomNavigationObs)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.destroyView()
        super.onDestroy()
    }
}