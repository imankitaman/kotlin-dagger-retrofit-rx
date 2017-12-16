package com.ankitaman.kotlinwithgit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RepositorysAdapter.Listener {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        val repository = SearchRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
                repository.searchUsersRepo(userName = "imankitaman")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe ({ result ->
                            handleResponse(result)
                            Log.d("Result", "There are ${result.size} Repositories Log")

                        }, { error ->
                            error.printStackTrace()
                        })
        )

    }


    private fun handleResponse(repoList: List<Response>) {
        val mAdapter = RepositorysAdapter(repoList, this)
        rv_repository.adapter = mAdapter
    }


    private fun initRecyclerView() {
        rv_repository.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_repository.layoutManager = layoutManager
    }

    override fun onItemClick(repo: Response) {

        Toast.makeText(this@MainActivity,"Repo Name ${repo.name}",Toast.LENGTH_SHORT).show()

    }



    override fun onDestroy() {
        if (compositeDisposable.isDisposed)
            compositeDisposable.clear()
        super.onDestroy()
    }
}
