package com.ankitaman.kotlinwithgit.extensions

import android.net.ConnectivityManager

/**
 * Created by ankitaman on 18/12/17.
 */

val ConnectivityManager.isConnected: Boolean
    get() = activeNetworkInfo?.isConnected ?: false


object NoNetworkException : Exception()