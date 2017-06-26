package com.github.presenter.impl

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.TDog
import com.github.base.RxPresenter
import com.github.model.http.*
import com.github.presenter.contract.RepositoriesContract
import com.github.util.RxUtil

/**
 * Created by cuiyue on 2017/6/21.
 */
class RepositoriesPresenter : RxPresenter<RepositoriesContract.View>(), RepositoriesContract.Presenter {

    companion object {
        private const val ORGANIZATION_NAME = "Yalantis"
        private const val REPOS_TYPE = "public"
    }

    override fun loadRepositories() {
        addDisposable(ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(t: MutableList<Repository>) {
                        mView?.showRepositories(t)
                    }
                }))
    }

    override fun loadTDog() {
        addDisposable(ApiManager.loadTDog()
                .compose(RxUtil.rxSchedulerHelper<TDogResponse<MutableList<TDog>>>())
                .compose(RxUtil.handleResult<MutableList<TDog>>())
                .subscribeWith(object : CommonSubscriber<MutableList<TDog>>(mView!!) {
                    override fun onNext(t: MutableList<TDog>?) {
                        mView?.showTDog(t!!)
                    }
                }))
    }


}

