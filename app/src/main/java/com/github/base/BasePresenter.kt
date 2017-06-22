package com.github.base

/**
 * Presenter基类
 */
interface BasePresenter<in T : BaseView> {

    fun attachView(view: T)

    fun detachView()

}