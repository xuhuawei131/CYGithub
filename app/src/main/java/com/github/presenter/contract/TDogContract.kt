package com.github.presenter.contract

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.TDog
import com.github.base.BasePresenter
import com.github.base.BaseView

/**
 * Created by cuiyue on 2017/6/21.
 */
object TDogContract {

    interface View : BaseView {
        fun showTDog(togs: MutableList<TDog>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadTDog()
    }
}