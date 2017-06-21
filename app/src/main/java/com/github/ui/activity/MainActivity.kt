package com.github.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.R
import com.github.base.App
import com.github.log.LogUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var app = applicationContext as App
        app.addActivity(this)
    }
}
