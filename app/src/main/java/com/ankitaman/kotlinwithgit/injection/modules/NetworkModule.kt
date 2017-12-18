package com.ankitaman.kotlinwithgit.injection.modules

import android.content.Context
import android.net.ConnectivityManager
import com.ankitaman.kotlinwithgit.BuildConfig
import com.ankitaman.kotlinwithgit.network.GitHubApiService
import com.ankitaman.kotlinwithgit.GitHubApplication
import com.ankitaman.kotlinwithgit.extensions.NoNetworkException
import com.ankitaman.kotlinwithgit.extensions.isConnected
import com.ankitaman.kotlinwithgit.network.GitHubNetworkManager
import com.ankitaman.kotlinwithgit.ui.RepoListPresenter
import com.ankitaman.kotlinwithgit.ui.RepoListViewContract
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Singleton
import javax.net.ssl.SSLPeerUnverifiedException

/**
 * Created by ankitaman on 17/12/17.
 */
@Module
class NetworkModule{

    fun provideOkHttpBuilder(): OkHttpClient.Builder =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE}
                    )



    @Provides
    @Singleton
    fun providesOkHttpClient(app: GitHubApplication): OkHttpClient {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return provideOkHttpBuilder()
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()

                    if (!connectivityManager.isConnected) {
                        throw NoNetworkException
                    }
                    try {
                        chain.proceed(requestBuilder.build())
                    } catch (e: SocketTimeoutException) {
                        throw NoNetworkException
                    } catch (e: UnknownHostException) {
                        throw NoNetworkException
                    } catch (e: SSLPeerUnverifiedException) {
                        throw NoNetworkException
                    }
                }
                .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .build()
    }


    @Provides
    @Singleton
    fun provideRepoService(retrofit: Retrofit): GitHubApiService = retrofit.create(GitHubApiService::class.java)

    @Provides
    @Singleton
    fun provideRepoApi(githubApi: GitHubApiService) = GitHubNetworkManager(githubApi)

    @Provides
    fun provideRepoPresenter(repoPresenter: RepoListPresenter) : RepoListViewContract.Presenter = repoPresenter



}