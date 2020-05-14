package com.zhou.mvpdemo.p

import com.zhou.baselibrary.network.HttpCallback
import com.zhou.mvpdemo.contract.LoginContract
import com.zhou.mvpdemo.m.LoginModel

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter {

    //P类，持有M和V的引用
    // 为什么我要把 model 放在外面？一个业务类P，只会有一个model么？如果需要多个数据源呢？
    private var model: LoginContract.Model? = null
    private var view: LoginContract.View? = view

    override fun doLogin(username: String, password: String) {
        val v = view ?: return
        val m = model ?: return

        v.showLoading()
        m.doLogin(username, password, object : HttpCallback<String> {
            override fun onSuccess(result: String?) {
                v.hideLoading()
                v.handleLoginResult(result)
            }

            override fun onFailure(e: Exception?) {
                v.hideLoading()
                v.onError(e.toString())
            }

        })
    }

    override fun onCreate() {
        model = LoginModel()
    }

    override fun onDestroy() {
        model = null
        view = null
    }
}