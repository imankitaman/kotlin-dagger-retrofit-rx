package com.ankitaman.kotlinwithgit

/**
 * Created by ankitaman on 17/12/17.
 */
object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GitHubApiService.Factory.create())
    }

}