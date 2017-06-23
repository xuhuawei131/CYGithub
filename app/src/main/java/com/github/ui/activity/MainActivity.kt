package com.github.ui.activity

import android.os.Bundle
import android.view.View
import client.yalantis.com.githubclient.model.Owner
import client.yalantis.com.githubclient.model.Repository
import com.github.R
import com.github.base.BaseActivity
import com.github.log.LogUtil
import com.github.model.http.GitHubResponse
import com.github.presenter.contract.RepositoriesContract
import com.github.presenter.impl.RepositoriesPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<RepositoriesContract.View, RepositoriesPresenter>(), RepositoriesContract.View {


    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        tv.setText("hahaha")
    }

    override fun initData() {
        mPresenter.loadRepositories()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showRepositories(repositories: MutableList<Repository>) {
        LogUtil.d("abc", "showRepositories-->" + repositories.size)

    }

    override fun showError() {
        LogUtil.d("abc", "showError-->")
    }

    override fun showErrorMsg(msg: String) {
        LogUtil.d("abc", "showErrorMsg-->" + msg)
    }

    override fun showEmpty() {
    }


}
