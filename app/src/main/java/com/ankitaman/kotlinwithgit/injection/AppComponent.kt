package com.ankitaman.kotlinwithgit.injection

import com.ankitaman.kotlinwithgit.injection.modules.AppModule
import com.ankitaman.kotlinwithgit.injection.modules.NetworkModule
import com.ankitaman.kotlinwithgit.ui.RepoListActivity
import com.ankitaman.kotlinwithgit.ui.RepoListPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ankitaman on 17/12/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class,NetworkModule::class))
interface AppComponent {

      fun inject(repoListActivity: RepoListActivity)

      fun inject(repoPresenter: RepoListPresenter)
}