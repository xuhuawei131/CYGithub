package com.github.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import client.yalantis.com.githubclient.model.Repository
import com.github.R
import com.github.base.BaseFragment
import com.github.log.LogUtil
import com.github.presenter.contract.RepositoriesContract
import com.github.presenter.impl.RepositoriesPresenter
import com.github.ui.adapter.GitHubListAdapter
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_face.*
import java.util.*

/**
 * Created by cuiyue on 2017/6/26.
 */
class FaceFragment : BaseFragment<RepositoriesContract.View, RepositoriesPresenter>(), RepositoriesContract.View {
    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    private var mAdapter: GitHubListAdapter? = null
    private var mRepositorys: MutableList<Repository>? = null

    override fun getLayout(): Int {
        return R.layout.fragment_face
    }

    override fun initView() {

        initXRecyclerview()
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

    /**
     * 初始化XRecyclerview
     */
    private fun initXRecyclerview() {
        mAdapter = GitHubListAdapter(ArrayList<Repository>(), {
            LogUtil.a("abc", "name---->" + it.name)
        })
        xrecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        xrecyclerview.adapter = mAdapter
        xrecyclerview.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                xrecyclerview.loadMoreComplete()
            }

            override fun onRefresh() {
                mRepositorys?.clear()
                mPresenter.loadRepositories()
            }
        })
    }

    override fun showRepositories(repositories: MutableList<Repository>) {
        mRepositorys = repositories
        mAdapter?.addRepositories(repositories)
        mAdapter?.notifyDataSetChanged()
        xrecyclerview.refreshComplete()
    }

}