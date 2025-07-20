package com.mahshad.authenticatorapp.welcome.data.localdatasource

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.mahshad.authenticatorapp.di.IoScheduler
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
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

    override fun saveUsername(username: String): Completable {
        return Completable.fromAction {
            rxSharedPreferences.getString(USERNAME_KEY).set(username)
        }
            .subscribeOn(ioScheduler)
    }

    override fun savePassword(password: String): Completable {
        return Completable.fromAction {
            rxSharedPreferences.getString(PASSWORD_KEY).set(password)
        }
            .subscribeOn(ioScheduler)
    }
}