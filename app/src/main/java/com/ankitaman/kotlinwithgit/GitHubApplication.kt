package com.ankitaman.kotlinwithgit

import android.app.Application
import com.ankitaman.kotlinwithgit.injection.AppComponent
import com.ankitaman.kotlinwithgit.injection.DaggerAppComponent
import com.ankitaman.kotlinwithgit.injection.modules.AppModule

/**
 * Created by ankitaman on 17/12/17.
 */
class GitHubApplication: Application() {

    companion object {
        @JvmStatic lateinit var instance: GitHubApplication
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildDaggerComponent()

    }


    private fun buildDaggerComponent() {

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

//        appComponent.inject(this)
    }


}