package com.ankitaman.kotlinwithgit.injection.modules

import android.content.Context
import com.ankitaman.kotlinwithgit.GitHubApplication
import com.ankitaman.kotlinwithgit.ui.RepoListPresenter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ankitaman on 17/12/17.
 */

@Module
class AppModule(private val application: GitHubApplication){

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application


    @Provides
    @Singleton
    fun providesApplication(): GitHubApplication = application

    @Provides
    @Singleton
    fun providesGson(): Gson =
            GsonBuilder()
                    .serializeNulls()
                    .create()




}