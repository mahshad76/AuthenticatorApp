package com.mahshad.authenticatorapp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.mahshad.authenticatorapp.data.local.UsernameSharedPrefImpl.Keys.KEY_USERNAME
import com.mahshad.authenticatorapp.di.IoScheduler
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

private const val PREFS_FILE_NAME = "my_app_prefs"

@Singleton
class UsernameSharedPrefImpl @Inject constructor(
    private val context: Context,
    @IoScheduler private val ioScheduler: Scheduler
) :
    UsernameSharedPref {
    private lateinit var sharedPrefs: SharedPreferences

    object Keys {
        const val KEY_USERNAME = "username"
    }

    fun init() {
        sharedPrefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
    }

    override fun insertUser(username: String): Completable {
        return Completable.create { emitter ->
            try {
                sharedPrefs.edit { putString(KEY_USERNAME, username) }
                emitter.onComplete()

            } catch (e: Exception) {
                emitter.onError(e)

            }

        }.subscribeOn(ioScheduler)
    }

    override fun deleteUser(): Completable {
        return Completable.create { emitter ->
            try {
                sharedPrefs.edit { clear() }
                emitter.onComplete()

            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(ioScheduler)
    }

    override fun getUser(): Maybe<String> {
        return Maybe.create { emitter ->
            try {
                val username = sharedPrefs.getString(Keys.KEY_USERNAME, null)
                if (username != null) emitter.onSuccess(username) else emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(ioScheduler)
    }
}