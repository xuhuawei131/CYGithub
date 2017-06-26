package com.github.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import client.yalantis.com.githubclient.model.Owner
import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.TDog
import com.github.R
import com.github.base.BaseActivity
import com.github.base.SimpleActivity
import com.github.log.LogUtil
import com.github.model.http.GitHubResponse
import com.github.presenter.contract.RepositoriesContract
import com.github.presenter.impl.RepositoriesPresenter
import kotlinx.android.synthetic.main.activity_main.*

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
        tv.setOnClickListener {
            var intent = Intent(this, TDogActivity::class.java)
            startActivity(intent)
        }
    }

}
