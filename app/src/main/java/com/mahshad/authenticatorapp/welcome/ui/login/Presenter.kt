package com.mahshad.authenticatorapp.welcome.ui.login

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
}