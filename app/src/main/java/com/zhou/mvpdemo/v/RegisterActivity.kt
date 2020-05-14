package com.zhou.mvpdemo.v

import android.util.Log
import android.view.View
import com.zhou.baselibrary.v.BaseActivity
import com.zhou.mvpdemo.R
import com.zhou.mvpdemo.contract.RegisterContract
import com.zhou.mvpdemo.m.bean.register.RegisterBean
import com.zhou.mvpdemo.p.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterContract.Presenter>(), RegisterContract.View {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun init() {

        btnRegister.setOnClickListener {
            val username = tvUsername.text.trim().toString()
            val password = tvPassword.text.trim().toString()
            val repassword = tvRepassword.text.trim().toString()
            castPresenter().doRegister(username = username, pwd = password, rpwd = repassword)
        }

    }

    override fun castPresenter(): RegisterContract.Presenter {
        return mPresenter as RegisterContract.Presenter
    }

    override fun bindPresenter() {
        mPresenter = RegisterPresenter(this)
    }

    /**
     * 处理注册结果
     */
    override fun handlerRegisterResult(data: RegisterBean?) {
        dataView.text = data.toString()
        Log.d("handlerRegisterResult", "$data")
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onError(msg: String) {
        dataView.text = msg
    }
}
