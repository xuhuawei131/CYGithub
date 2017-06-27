package com.github.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import client.yalantis.com.githubclient.flow.repository.RepositoryDetailsContract
import client.yalantis.com.githubclient.model.RepositoryDetail
import com.github.R
import com.github.base.BaseActivity
import com.github.presenter.impl.RepositoryDetailsPresenter
import kotlinx.android.synthetic.main.activity_repository_detail.*

class RepositoryDetailActivity : BaseActivity<RepositoryDetailsContract.View, RepositoryDetailsPresenter>(), RepositoryDetailsContract.View {

    override var mPresenter: RepositoryDetailsPresenter = RepositoryDetailsPresenter()

    companion object {
        private const val NAME = "name"

        fun newIntent(context: Context, name: String): Intent =
                Intent(context, RepositoryDetailActivity::class.java).apply {
                    putExtra(NAME, name)
                }
    }

    override fun getLayout(): Int = R.layout.activity_repository_detail

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError() {
    }

    override fun showErrorMsg(msg: String) {
    }

    override fun showEmpty() {
    }

    override fun showRepositoryDetails(repositoryDetail: RepositoryDetail) {
        tv_textStars.text = repositoryDetail.stargazers_count
        tv_textForks.text = repositoryDetail.forks_count
    }

    override fun initView() {
    }

    override fun initData() {
        var name = intent.getStringExtra(NAME)
        mPresenter.loadRepositoryDetails("kotlin", name)
    }

}
