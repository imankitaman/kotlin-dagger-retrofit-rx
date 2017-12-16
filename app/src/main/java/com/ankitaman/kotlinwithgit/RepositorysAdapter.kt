package com.ankitaman.kotlinwithgit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.repository_item.view.*

/**
 * Created by ankitaman on 17/12/17.
 */
class RepositorysAdapter(private val repositoryList: List<Response>,
                         private val itemClick: Listener) : RecyclerView.Adapter<RepositorysAdapter.RepositoryViewHolder>() {


    interface Listener {
        fun onItemClick(repo : Response)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.repository_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = repositoryList.size


    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(response = repositoryList[position],itemClick = itemClick)
    }


    // Note Unit = Void in java
    class RepositoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(response: Response, itemClick: Listener){
            with(response){
                itemView.tv_repo_name.text = name
                itemView.tv_repo_language.text = language
                itemView.setOnClickListener{itemClick.onItemClick(this)}
            }
        }
    }
}