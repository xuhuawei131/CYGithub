package com.github.presenter.impl

import client.yalantis.com.githubclient.model.Repository
import com.github.base.RxPresenter
import com.github.log.LogUtil
import com.github.model.http.ApiManager
import com.github.model.http.CommonSubscriber
import com.github.presenter.contract.RepositoriesContract
import com.github.util.RxUtil

/**
 * Created by cuiyue on 2017/6/21.
 */
class RepositoriesPresenter : RxPresenter<RepositoriesContract.View>(), RepositoriesContract.Presenter {

    companion object {
        private const val ORGANIZATION_NAME = "kotlin"
        private const val REPOS_TYPE = "public"
    }

    override fun loadRepositories() {
        addDisposable(ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE, "1", "10")
                .compose(RxUtil.rxSchedulerHelper<MutableList<Repository>>())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(t: MutableList<Repository>?) {
                        mView?.showRepositories(t!!)
                    }
                }))
    }

}

