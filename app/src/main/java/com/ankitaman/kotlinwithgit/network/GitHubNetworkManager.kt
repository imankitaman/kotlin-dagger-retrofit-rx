package com.ankitaman.kotlinwithgit.network

import com.ankitaman.kotlinwithgit.network.model.RepoResponse
import javax.inject.Inject

/**
 * Created by ankitaman on 17/12/17.
 */
class GitHubNetworkManager @Inject constructor(val apiService: GitHubApiService){

    fun searchUsersRepo(userName : String): io.reactivex.Observable<List<RepoResponse>> {
        return apiService.searchUserRepos(userName = userName)
    }

}