package com.github.presenter.impl

import android.os.Handler
import client.yalantis.com.githubclient.flow.repository.RepositoryDetailsContract
import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.RepositoryDetail
import com.github.base.RxPresenter
import com.github.model.http.ApiManager
import com.github.model.http.CommonSubscriber
import com.github.util.RxUtil


/**
 * Created by cuiyue on 2017/6/21.
 */
class RepositoryDetailsPresenter : RxPresenter<RepositoryDetailsContract.View>(), RepositoryDetailsContract.Presenter {

    override fun loadRepositoryDetails(owner: String, name: String) {
        mView?.showProgress()
        addDisposable(ApiManager.loadRepository(owner, name)
                .compose(RxUtil.rxSchedulerHelper<RepositoryDetail>())
                .subscribeWith(object : CommonSubscriber<RepositoryDetail>(mView!!) {
                    override fun onNext(t: RepositoryDetail?) {
                        mView?.hideProgress()
                        mView?.showRepositoryDetails(t!!)
                    }
                }))
    }


}

