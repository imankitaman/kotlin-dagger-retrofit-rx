package com.ankitaman.kotlinwithgit.ui

import android.util.Log
import com.ankitaman.kotlinwithgit.GitHubApplication
import com.ankitaman.kotlinwithgit.extensions.NoNetworkException
import com.ankitaman.kotlinwithgit.extensions.addToCompositeDisposable
import com.ankitaman.kotlinwithgit.network.GitHubNetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ankitaman on 17/12/17.
 */
class RepoListPresenter : RepoListViewContract.Presenter{

@Inject constructor(){

}
    private var compositeDisposable = CompositeDisposable()

    @Inject lateinit var  apiManager : GitHubNetworkManager

    var view: RepoListViewContract.View? = null

    init {
        GitHubApplication.appComponent.inject(this)
    }

    override fun attachView(view: RepoListViewContract.View) {
        this.view = view
    }

    override fun detachView(view: RepoListViewContract.View) {

    }
    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun loadUserRepos() {
        view?.let {
            it.showOrHideProgressBar(true)
                    apiManager.searchUsersRepo(userName = "imankitaman")
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .doOnError(Consumer { view?.showOrHideProgressBar(false) })
                            .doOnComplete { view?.showOrHideProgressBar(false) }
                            .subscribe ({ result ->
                                view?.showRepos(result)
                                Log.d("Result", "There are ${result.size} Repositories Log")
                            }, { error ->
                                when(error) {
                                    is NoNetworkException -> view?.showAnyError("No Internet")
                                    else ->
                                        view?.showAnyError(error.localizedMessage)
                                }
                            }).addToCompositeDisposable(compositeDisposable)// using kotlin extension to directly add disposable

        }
    }



}