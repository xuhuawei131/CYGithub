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

    private var mAllActivities: MutableSet<Activity>? = null

    override fun onCreate() {
        super.onCreate()

        var isApplicationRepeat = Utils.isApplicationRepeat(this);
        if (isApplicationRepeat) {
            return
        }

        //初始化Logger的TAG
        LogUtil.init(BuildConfig.DEBUG)
        // dex突破65535的限制
        MultiDex.install(this)
    }

    fun addActivity(act: Activity) {

        if (mAllActivities == null) {
            mAllActivities = mutableSetOf<Activity>()
        }

        LogUtil.e("abc", "size-->" + mAllActivities!!.size)
    }

}