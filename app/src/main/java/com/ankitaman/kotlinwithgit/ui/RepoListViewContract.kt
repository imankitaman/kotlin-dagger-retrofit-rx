package com.ankitaman.kotlinwithgit.ui

import com.ankitaman.kotlinwithgit.BasePresenter
import com.ankitaman.kotlinwithgit.BaseView
import com.ankitaman.kotlinwithgit.network.model.RepoResponse

/**
 * Created by ankitaman on 17/12/17.
 */
interface RepoListViewContract {


    interface View :BaseView {

        fun showOrHideProgressBar(show: Boolean)
        fun showRepos(repoList: List<RepoResponse>)
        fun showAnyError(errorMessage :String)
    }


    interface Presenter : BasePresenter<View> {
        fun loadUserRepos()
    }

}