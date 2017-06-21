package com.github.base

import android.app.Activity
import android.os.Bundle
import com.github.event.DummyEvent
import me.yokeyword.fragmentation.SupportActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by cuiyue on 2017/6/21.
 */
abstract class SimpleActivity : SupportActivity() {

    protected val mContext: Activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        var app = applicationContext as App
        app.addActivity(mContext)
        EventBus.getDefault().register(this)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(mContext)
        var app = applicationContext as App
        app.removeActivity(mContext)
    }

    /**
     * 初始化子类布局
     */
    protected abstract fun getLayout(): Int

    /**
     * 初始化子类View
     */
    protected abstract fun initView()

    /**
     * 初始化子类一些数据
     */
    protected abstract fun initData()

    /**
     * 该方法不执行，只是让Event编译通过
     */
    @Subscribe
    fun dummy(event: DummyEvent) {
    }
}