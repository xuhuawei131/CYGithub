package com.github.util

import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import com.github.base.App
import java.io.File

/**
 * Created by cuiyue on 2017/6/21.
 */
object Utils {

    /**
     * 判断程序是否重复启动
     */
    fun isApplicationRepeat(applicationContext: Context): Boolean {

        val pid = android.os.Process.myPid()
        var processName: String? = null
        val am = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val l = am.runningAppProcesses
        val i = l.iterator()
        while (i.hasNext()) {
            val info = i.next()
            try {
                if (info.pid == pid) {
                    processName = info.processName
                }
            } catch (e: Exception) {
            }

        }
        return processName == null || !processName.equals(applicationContext.packageName, ignoreCase = true)
    }

    /**
     * 判断网络是否好用
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getActiveNetworkInfo() != null
    }

    /**
     * 得到缓存总目录
     */
    fun getDataPath(): String {
        return App.instance.cacheDir.absolutePath + File.separator + "data"
    }

    /**
     * 得到网络缓存目录
     */
    fun getCachePath(): String {
        return getDataPath() + File.separator + "netCache"
    }

}