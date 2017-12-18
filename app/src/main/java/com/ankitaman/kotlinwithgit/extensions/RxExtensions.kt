package com.ankitaman.kotlinwithgit.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ankitaman on 18/12/17.
 */

fun Disposable.addToCompositeDisposable(composite: CompositeDisposable) {
    composite.add(this)
}