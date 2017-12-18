package com.ankitaman.kotlinwithgit.network

import com.ankitaman.kotlinwithgit.network.model.RepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ankitaman on 17/12/17.
 */
interface GitHubApiService {


    @GET("users/{userName}/repos")
    fun searchUserRepos(@Path("userName") userName: String): Observable<List<RepoResponse>>


}