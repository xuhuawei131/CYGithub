package com.github.presenter.impl

import client.yalantis.com.githubclient.model.TDog
import com.github.base.RxPresenter
import com.github.model.http.ApiManager
import com.github.model.http.CommonSubscriber
import com.github.model.http.TDogResponse
import com.github.presenter.contract.TDogContract
import com.github.util.RxUtil

/**
 * Created by cuiyue on 2017/6/21.
 */
class TDogPresenter : RxPresenter<TDogContract.View>(), TDogContract.Presenter {

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

