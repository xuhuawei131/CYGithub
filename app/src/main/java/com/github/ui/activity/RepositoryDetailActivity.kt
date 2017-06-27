package com.github.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import client.yalantis.com.githubclient.flow.repository.RepositoryDetailsContract
import client.yalantis.com.githubclient.model.RepositoryDetail
import com.github.R
import com.github.base.BaseActivity
import com.github.presenter.impl.RepositoryDetailsPresenter
import kotlinx.android.synthetic.main.activity_repository_detail.*

class RepositoryDetailActivity : BaseActivity<RepositoryDetailsContract.View, RepositoryDetailsPresenter>(), RepositoryDetailsContract.View {

    override var mPresenter: RepositoryDetailsPresenter = RepositoryDetailsPresenter()

    companion object {
        private const val URL = "url"

        fun newIntent(context: Context, url: String): Intent =
                Intent(context, RepositoryDetailActivity::class.java).apply {
                    putExtra(URL, url)
                }
    }

    override fun getLayout(): Int = R.layout.activity_repository_detail

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError() {
    }

    override fun showErrorMsg(msg: String) {
    }

    override fun showEmpty() {
    }

    override fun showRepositoryDetails(repositoryDetail: RepositoryDetail) {
//        tv_textStars.text = repositoryDetail.stargazers_count
//        tv_textForks.text = repositoryDetail.forks_count
    }

    override fun initView() {
        initWebView()
    }

    override fun initData() {
//        mPresenter.loadRepositoryDetails("kotlin", "ts2kt")
    }

    private fun initWebView() {
        setWebViewSettings()
        setWebView()
    }

    private fun setWebViewSettings() {

        val webSettings = wv.getSettings()
        // 打开页面时， 自适应屏幕
        webSettings.setUseWideViewPort(true) //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true) // 缩放至屏幕的大小
        // 便页面支持缩放
        webSettings.setJavaScriptEnabled(true) //支持js
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true)
        webSettings.setDomStorageEnabled(true)//解决HTML显示不全的问题
        webSettings.setSupportZoom(true) //支持缩放
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE)
    }

    private fun setWebView() {
        var url = intent.getStringExtra(URL)
        wv.loadUrl(url)
        wv.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (url != null) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
            }
        })
    }


}
