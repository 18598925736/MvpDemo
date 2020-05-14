package com.zhou.mvpdemo.v

import android.content.Intent
import android.view.View
import com.zhou.mvpdemo.p.MainPagePresenter
import com.zhou.baselibrary.v.BaseActivity
import com.zhou.mvpdemo.R
import com.zhou.mvpdemo.contract.MainContract
import com.zhou.mvpdemo.m.bean.articles.ArticleBean
import com.zhou.mvpdemo.m.bean.banner.BannerBean
import kotlinx.android.synthetic.main.activity_main.*

/**
 * View层一个标准实现
 *
 * MainActivity: 一个app的主面板
 */
class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun bindPresenter() {
        mPresenter = MainPagePresenter(this)
    }

    override fun castPresenter(): MainPagePresenter {
        return mPresenter as MainPagePresenter
    }

    override fun init() {
        btn1.setOnClickListener {
            val data = castPresenter().getArticle()
            if (data != null) {
                dataView.text = data.toString()
            } else {
                onError("文章list请求失败")
            }
        }

        btn2.setOnClickListener {
            val data = castPresenter().getBanner()
            if (data != null) {
                dataView.text = data.toString()
            } else {
                onError("banner图请求失败")
            }
        }

        btnToSetting.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun handlerArticles(bean: ArticleBean?) {
        dataView.text = bean.toString() //  我只能 打印他的toString, 但是如果我想
    }

    override fun handlerBanners(bean: BannerBean?) {
        dataView.text = bean.toString() //  我只能 打印他的toString, 但是如果我想
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onError(msg: String) {
        dataView.text = "获取内容失败!"
    }

}
