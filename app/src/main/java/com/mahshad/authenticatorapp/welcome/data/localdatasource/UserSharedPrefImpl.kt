package com.mahshad.authenticatorapp.welcome.data.localdatasource

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.mahshad.authenticatorapp.di.IoScheduler
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSharedPrefImpl @Inject constructor(
    context: Context, @IoScheduler
    private val ioScheduler: Scheduler
) : UserSharedPref {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
    private val rxSharedPreferences: RxSharedPreferences = RxSharedPreferences.create(sharedPref)

    companion object {
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
    }

    override fun readUsername(): Maybe<String?> {
        return Maybe.fromCallable {
            rxSharedPreferences.getString(USERNAME_KEY).get()
        }
            .subscribeOn(ioScheduler)
    }

    override fun readPassword(): Maybe<String?> {
        return Maybe.fromCallable {
            rxSharedPreferences.getString(PASSWORD_KEY).get()
        }
            .subscribeOn(ioScheduler)
    }

    override fun saveUsername(username: String): Disposable {
        return Completable.fromAction {
            rxSharedPreferences.getString(USERNAME_KEY).set(username)
        }
            .subscribeOn(ioScheduler)
            .subscribe { Log.d("TAG", "saveUsername") }
    }

    override fun savePassword(password: String): Disposable {
        return Completable.fromAction {
            rxSharedPreferences.getString(PASSWORD_KEY).set(password)
        }
            .subscribeOn(ioScheduler)
            .subscribe { Log.d("TAG", "savePassword") }
    }
}