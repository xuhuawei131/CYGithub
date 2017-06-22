package com.github.presenter.impl

import android.util.Log
import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.RepositoryDetail
import com.github.base.CommonSubscriber
import com.github.base.RxPresenter
import com.github.model.http.ApiManager
import com.github.presenter.contract.RepositoriesContract
import com.github.util.RxUtil

/**
 * Created by cuiyue on 2017/6/21.
 */
class RepositoriesPresenter : RxPresenter<RepositoriesContract.View>(), RepositoriesContract.Presenter {

    companion object {
        private val ORGANIZATION_NAME = "Yalantis"
        private val REPOS_TYPE = "public"
    }

    override fun loadRepositories() {

        var disposable = ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE)
                .compose(RxUtil.rxSchedulerHelper<MutableList<Repository>>())
                .subscribeWith(object : CommonSubscriber<MutableList<Repository>>(mView!!) {
                    override fun onNext(repositorys: MutableList<Repository>) {
                        Log.d("abc", repositorys.toString())
                    }
                })


        addDisposable(disposable)
    }


}