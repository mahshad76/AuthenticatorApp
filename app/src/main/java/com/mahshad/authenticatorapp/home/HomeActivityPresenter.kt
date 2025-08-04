package com.mahshad.authenticatorapp.home

import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.common.replaceFragment
import com.mahshad.authenticatorapp.home.di.HomeActivityDisposable
import com.mahshad.authenticatorapp.home.di.Scopes
import com.mahshad.authenticatorapp.home.ui.favorite.FavoriteFragment
import com.mahshad.authenticatorapp.home.ui.home.HomeFragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@Scopes.HomeActivityScope
class HomeActivityPresenter @Inject constructor(
    @HomeActivityDisposable
    private val compositeDisposable: CompositeDisposable
) :
    HomeActivityContract.Presenter {
    private var view: HomeActivityContract.View? = null
    override fun replaceFragmentCall(bottomNavigationObs: Observable<MenuItem>) {
        val disposable = bottomNavigationObs
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
        compositeDisposable.add(disposable)
    }

    override fun attachView(view: HomeActivityContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        compositeDisposable.clear()
    }
}


