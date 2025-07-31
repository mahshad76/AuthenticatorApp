package com.mahshad.authenticatorapp.home

import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.AppCompatActivityExtensions.replaceFragment
import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.ui.favorite.FavoriteFragment
import com.mahshad.authenticatorapp.home.ui.home.HomeFragment
import io.reactivex.Observable
import javax.inject.Inject

@Scopes.HomeActivityScope
class HomeActivityPresenter @Inject constructor() : HomeActivityContract.Presenter {
    private var view: HomeActivityContract.View? = null
    override fun replaceFragmentCall(bottomNavigationObs: Observable<MenuItem>) {
        bottomNavigationObs
            .subscribe { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_home -> (view as AppCompatActivity)?.replaceFragment(
                        R.id.nav_host_fragment_activity_home2,
                        HomeFragment()
                    )

                    R.id.navigation_favorite -> (view as AppCompatActivity)?.replaceFragment(
                        R.id.nav_host_fragment_activity_home2,
                        FavoriteFragment()
                    )

                    R.id.navigation_notifications -> {
                        Log.d("BottomNav", "Notifications selected")
                    }
                }
            }
    }

    override fun attachView(view: HomeActivityContract.View) {
        this.view = view
    }

    override fun detachView() {
        TODO("Not yet implemented")
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }
}


