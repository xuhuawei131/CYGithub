package com.github.ui.activity

import android.os.Bundle
import com.github.R
import com.github.base.SimpleActivity
import kotlinx.android.synthetic.main.activity_main.*
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

class MainActivity : SimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView() {
        initBottomNavigationView()
    }

    /**
     * 初始化底部导航栏
     */
    private fun initBottomNavigationView() {
        bnve.enableAnimation(false)
        bnve.enableShiftingMode(false)
        bnve.enableItemShiftingMode(false)
        addBadgeAt(2, 1)
    }

    /**
     * 给底部导航栏增加气泡
     */
    private fun addBadgeAt(position: Int, number: Int): Badge {
        // add badge
        return QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12F, 2F, true)
                .bindTarget(bnve.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener { dragState, badge, targetView ->
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED === dragState) {
                        //清除气泡的回调
                    }
                }
    }

}
