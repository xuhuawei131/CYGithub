package com.github.presenter.impl

import client.yalantis.com.githubclient.model.Repository
import com.github.base.RxPresenter
import com.github.log.LogUtil
import com.github.model.http.ApiManager
import com.github.model.http.CommonSubscriber
import com.github.presenter.contract.RepositoriesContract
import com.github.util.RxUtil
import android.R.attr.delay
import android.os.Handler


/**
 * Created by cuiyue on 2017/6/21.
 */
class RepositoriesPresenter : RxPresenter<RepositoriesContract.View>(), RepositoriesContract.Presenter {


    private val NUM_OF_PAGE = "10"
    private var mCurrentPage = 1

    companion object {
        private const val ORGANIZATION_NAME = "kotlin"
        private const val REPOS_TYPE = "public"
    }

    override fun loadRepositories() {
        mView?.showProgress()
        addDisposable(ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE, "1", NUM_OF_PAGE)
                .compose(RxUtil.rxSchedulerHelper<MutableList<Repository>>())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(t: MutableList<Repository>?) {
                        mView?.hideProgress()
                        mView?.showRepositories(t!!)
                    }
                }))
    }

    override fun loadRefreshRepositories() {
        mCurrentPage = 1
        addDisposable(ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE, mCurrentPage.toString(), NUM_OF_PAGE)
                .compose(RxUtil.rxSchedulerHelper<MutableList<Repository>>())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(t: MutableList<Repository>?) {
                        mView?.showRefreshRepositories(t!!)
                    }
                }))
    }

    override fun loadMoreRepositories() {
        mCurrentPage++
        addDisposable(ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE, mCurrentPage.toString(), NUM_OF_PAGE)
                .compose(RxUtil.rxSchedulerHelper<MutableList<Repository>>())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(t: MutableList<Repository>?) {
                        mView?.showMoreRepositories(t!!)
                    }
                }))
    }


}

