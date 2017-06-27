package com.github.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import client.yalantis.com.githubclient.model.Repository
import com.github.R
import kotlinx.android.synthetic.main.item_repository.view.*

class GitHubListAdapter(private val repositories: MutableList<Repository>,
                        val onClick: (Repository) -> Unit)
    : RecyclerView.Adapter<GitHubListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_repository, parent, false).let {
            ViewHolder(it, onClick)
        }
    }

    class ViewHolder(itemView: View, val onClick: (Repository) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bindData(repository: Repository) {
            with(repository) {
                itemView.text_view_title.text = name
                itemView.text_view_description.text = description
                itemView.setOnClickListener { onClick(this) }
            }
        }
    }

    fun addRepositories(newRepositories: List<Repository>) {
        repositories.addAll(newRepositories)
    }
}