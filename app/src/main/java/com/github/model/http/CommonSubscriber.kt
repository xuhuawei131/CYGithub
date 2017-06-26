package com.github.model.http

import android.text.TextUtils
import android.util.Log
import com.github.base.BaseView
import com.github.log.LogUtil
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException

/**
 * Created by cuiyue on 2017/6/22.
 */
abstract class CommonSubscriber<T> : ResourceSubscriber<T> {

    private var mView: BaseView
    private var mErrorMsg: String = ""
    private var isShowErrorState: Boolean = true

    constructor(view: BaseView) {
        this.mView = view
    }

    constructor(view: BaseView, mErrorMsg: String) : this(view) {
        this.mView = view
        this.mErrorMsg = mErrorMsg
    }

    constructor(view: BaseView, isShowErrorState: Boolean) : this(view) {
        this.mView = view
        this.isShowErrorState = isShowErrorState
    }

    constructor(view: BaseView, mErrorMsg: String, isShowErrorState: Boolean) : this(view) {
        this.mView = view
        this.mErrorMsg = mErrorMsg
        this.isShowErrorState = isShowErrorState
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable?) {

        if (mView == null) {
            return
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg)
        } else if (e is ApiException) {
            mView.showErrorMsg(e.toString())
        } else if (e is HttpException) {
            mView.showErrorMsg("数据加载失败ヽ(≧Д≦)ノ")
        } else {
            mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ")
        }
        if (isShowErrorState) {
            mView.showError()
        }
    }

}