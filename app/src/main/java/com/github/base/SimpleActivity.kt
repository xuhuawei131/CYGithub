package com.github.base

import android.app.Activity
import android.os.Bundle
import com.github.R
import com.github.event.DummyEvent
import com.jaeger.library.StatusBarUtil
import me.yokeyword.fragmentation.SupportActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by cuiyue on 2017/6/21.
 */
abstract class SimpleActivity : SupportActivity() {

    protected val mActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setStatusBar()
        App.instance.addActivity(mActivity)
        EventBus.getDefault().register(mActivity)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(mActivity)
        App.instance.removeActivity(mActivity)
    }

    /**
     * 设置状态栏
     */
    protected fun setStatusBar() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary))
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