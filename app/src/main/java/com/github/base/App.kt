package com.github.base

import android.app.Activity
import android.app.Application
import android.support.multidex.MultiDex
import com.github.BuildConfig
import com.github.log.LogUtil
import com.github.util.Utils

/**
 * Created by cuiyue on 2017/6/21.
 */
class App : Application() {

    private val mAllActivities: MutableSet<Activity> = mutableSetOf<Activity>()

    override fun onCreate() {
        super.onCreate()

        //判断程序是否重复启动
        var isApplicationRepeat = Utils.isApplicationRepeat(this);
        if (isApplicationRepeat) {
            return
        }

        //初始化Logger的TAG
        LogUtil.init(BuildConfig.DEBUG)
        // dex突破65535的限制
        MultiDex.install(this)
    }

    /**
     * 保存Activity的引用
     */
    fun addActivity(act: Activity) {
        mAllActivities.add(act)
    }

    /**
     * 清除Activity的引用
     */
    fun removeActivity(act: Activity) {
        mAllActivities.remove(act)
    }

    /**
     * 退出App
     */
    fun exitApp() {
        synchronized(mAllActivities) {
            for (act in mAllActivities) {
                act.finish()
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

}