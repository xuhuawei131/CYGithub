package com.github.ui.fragment

import client.yalantis.com.githubclient.model.Repository
import com.github.R
import com.github.base.BaseFragment
import com.github.log.LogUtil
import com.github.presenter.contract.RepositoriesContract
import com.github.presenter.impl.RepositoriesPresenter

/**
 * Created by cuiyue on 2017/6/26.
 */
class FaceFragment : BaseFragment<RepositoriesContract.View, RepositoriesPresenter>(), RepositoriesContract.View {
    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    override fun getLayout(): Int {
        return R.layout.fragment_face
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
        LogUtil.d("abc", "repositories--->" + repositories.size)
    }

}