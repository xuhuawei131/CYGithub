package com.github.ui.activity

import android.os.Bundle
import client.yalantis.com.githubclient.model.Repository
import com.github.R
import com.github.base.BaseActivity
import com.github.presenter.contract.RepositoriesContract
import com.github.presenter.impl.RepositoriesPresenter

class MainActivity : BaseActivity<RepositoriesContract.View, RepositoriesPresenter>(), RepositoriesContract.View {


    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
        mPresenter.loadRepositories()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showError() {
    }

    override fun showErrorMsg(msg: String) {
    }

    override fun showEmpty() {
    }

    override fun showRepositories(repositories: MutableList<Repository>) {



    }


}
