package com.github.ui.activity

import client.yalantis.com.githubclient.model.TDog
import com.github.R
import com.github.base.BaseActivity
import com.github.log.LogUtil
import com.github.presenter.contract.TDogContract
import com.github.presenter.impl.TDogPresenter

class TDogActivity : BaseActivity<TDogContract.View, TDogPresenter>(), TDogContract.View {

    override var mPresenter: TDogPresenter = TDogPresenter()

    override fun getLayout(): Int {
        return R.layout.activity_tdog
    }

    override fun initView() {
    }

    override fun initData() {
        mPresenter.loadTDog()
    }

    override fun showTDog(togs: MutableList<TDog>) {
        LogUtil.d("abc", "togs--->" + togs.size)
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


}
