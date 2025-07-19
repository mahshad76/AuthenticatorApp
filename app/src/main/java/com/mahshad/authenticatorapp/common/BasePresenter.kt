package com.mahshad.authenticatorapp.common

interface BasePresenter<in T : BaseView> {
    /**
     * Attaches a view to the presenter. This method should be called when the view is created and
     * ready to display data.
     * Typically, this happens in the `onCreate` or `onViewCreated` method of the view's lifecycle.
     * @param view The view instance to be attached. It must be of the type specified by the
     * presenter's generic parameter `T`.
     */
    fun attachView(view: T)

    /**
     * * When the view is destroyed, this method must be called, so the presenter doesn't try to
     * interact with a destroyed view and the grabage collector can collect the
     * inactive view instance.
     */
    fun detachView()

    /**
     * This method must be called when the view is permanently destroyed, so active subscriptions
     * to the observables must be discarded.
     */
    fun destroyView()
}
