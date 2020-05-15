package com.zhou.mvpdemo.contract

import com.zhou.baselibrary.m.BaseModel
import com.zhou.baselibrary.p.BasePresenter
import com.zhou.baselibrary.v.BaseView
import com.zhou.mvpdemo.contract.m.NoticeTipsModel
import com.zhou.mvpdemo.contract.p.NoticeTipsPresenter

/**
 * 加入我要做一个消息通知的小模块，在各个Activity或者Fragment之间复用
 *
 * 就用网络的变化为例。
 */
class NoticeTipsContract {
    // 那么，先来看看这个数据来自哪里，消息从哪里来
    interface Model : BaseModel {
        fun getMsg(): String // 获取消息
    }

    /**
     * P 粘合剂 该怎么处理
     */
    interface Presenter : BasePresenter<BaseView> {
        fun updateMsg() // 控制层，更新消息
    }

    /**
     *
     */
    interface View : BaseView {
        fun updateMsg(msg: String)
    }

    companion object {
        fun getModel(): Model {
            return NoticeTipsModel()
        }

        fun getPresenter(view: View): Presenter {
            return NoticeTipsPresenter(view)
        }
    }
}