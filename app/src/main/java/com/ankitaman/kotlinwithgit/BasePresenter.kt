package com.ankitaman.kotlinwithgit

import android.view.View

/**
 * Created by ankitaman on 17/12/17.
 */
interface BasePresenter<in V :BaseView> {

    fun detachView(view: V)
    fun attachView(view: V)
    fun destroy()

}