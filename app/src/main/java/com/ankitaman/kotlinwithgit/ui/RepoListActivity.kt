package com.ankitaman.kotlinwithgit.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.ankitaman.kotlinwithgit.GitHubApplication
import com.ankitaman.kotlinwithgit.R
import com.ankitaman.kotlinwithgit.network.model.RepoResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RepoListActivity : AppCompatActivity(), RepoListViewContract.View, RepositorysAdapter.Listener {

    @Inject
     protected lateinit var presenter: RepoListViewContract.Presenter


//    private val adapter: RepositorysAdapter by lazy(LazyThreadSafetyMode.NONE) { RepositorysAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GitHubApplication.appComponent.inject(this)

        presenter.attachView(this)
        presenter.loadUserRepos()

        initRecyclerView()

    }



    private fun initRecyclerView() {
        rv_repository.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_repository.layoutManager = layoutManager
    }


    override fun showOrHideProgressBar(show: Boolean) {
      frame_loading.visibility= if (show) View.VISIBLE else View.GONE
    }

    override fun showRepos(repoList: List<RepoResponse>) {
        val mAdapter = RepositorysAdapter(repoList, this)
         rv_repository.adapter = mAdapter
    }

    override fun showAnyError(errorMessage: String) {
        Toast.makeText(this,"String",Toast.LENGTH_SHORT).show();
    }

    override fun onItemClick(repo: RepoResponse) {

        Toast.makeText(this@RepoListActivity,"Repo Name ${repo.name}",Toast.LENGTH_SHORT).show()

    }



    override fun onDestroy() {

        presenter.detachView(this)
        presenter.destroy()

        super.onDestroy()
    }
}
