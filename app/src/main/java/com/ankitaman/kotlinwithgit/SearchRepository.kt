package com.ankitaman.kotlinwithgit

/**
 * Created by ankitaman on 17/12/17.
 */
class SearchRepository(val apiService: GitHubApiService){

    fun searchUsersRepo(userName : String): io.reactivex.Observable<List<Response>> {
        return apiService.searchUserRepos(userName = userName)
    }

}