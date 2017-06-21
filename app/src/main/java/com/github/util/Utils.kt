package com.github.util

import android.app.ActivityManager
import android.content.Context

/**
 * Created by cuiyue on 2017/6/21.
 */
object Utils {

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

}