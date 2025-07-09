package com.mahshad.authenticatorapp.welcome.ui.login

import java.util.concurrent.TimeUnit

class Presenter : Contract.Presenter {
    private var view: Contract.View? = null

    override fun attachView(view: Contract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroyView() {
        TODO("Not yet implemented")
    }

    override fun usernameListener() {
        view?.observableUsernameEditText()
            ?.skip(1)
            ?.debounce(300, TimeUnit.MILLISECONDS)
            ?.filter { username: CharSequence -> !username.isEmpty() && username.length > 7 }
            ?.map { username: CharSequence -> username.trim().toString() }
            //?.switchMap { TODO("call a functions which return an observable") }

    }

    override fun passwordListener() {
        view?.observablePasswordEditText()
    }

    override fun loginButtonListener() {
        view?.observableLoginButton()
    }
}