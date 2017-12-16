package com.ankitaman.kotlinwithgit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ankitaman on 17/12/17.
 */
interface GitHubApiService {


    @GET("users/{userName}/repos")
    fun searchUserRepos(@Path("userName") userName: String): Observable<List<Response>>


    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): GitHubApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()

            return retrofit.create(GitHubApiService::class.java)
        }
    }

}