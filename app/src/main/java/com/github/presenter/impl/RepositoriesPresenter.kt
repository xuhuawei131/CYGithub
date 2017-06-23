package com.github.presenter.impl

import client.yalantis.com.githubclient.model.Repository
import com.github.model.http.CommonSubscriber
import com.github.base.RxPresenter
import com.github.model.http.ApiManager
import com.github.model.http.GitHubResponse
import com.github.model.http.MyHttpResponse
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
                .compose(RxUtil.rxSchedulerHelper<GitHubResponse<MutableList<Repository>>>())
                .compose(RxUtil.handleResult<MutableList<Repository>>())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(repositorys: MutableList<Repository>) {
                        mView?.showRepositories(repositorys)
                    }
                }))

//        var disposable = ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE)
//                .compose(RxUtil.rxSchedulerHelper())
//                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
//                    override fun onNext(repositorys: MutableList<Repository>) {
//                        mView?.showRepositories(repositorys)
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        super.onError(e)
//                    }
//                })
//        addDisposable(disposable)
    }


}

