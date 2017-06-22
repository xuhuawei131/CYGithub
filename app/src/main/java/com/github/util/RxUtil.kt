package com.github.util

import android.os.Handler
import android.os.Message
import com.github.log.LogUtil
import com.github.model.http.MyHttpResponse
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

/**
 * Created by cuiyue on 2017/6/22.
 */
object RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
    </T> */
    fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> {    //compose简化线程


        var flowableTransformer = object : FlowableTransformer<T, T> {
            override fun apply(upstream: Flowable<T>?): Publisher<T> {
                if (upstream == null){
                    LogUtil.d("abc","upstream == null")
                }


               return upstream?.subscribeOn(Schedulers.io())
                       ?.observeOn(AndroidSchedulers.mainThread())!!
            }

        }
        LogUtil.d("abc","rxSchedulerHelper--flowableTransformer")
        return flowableTransformer



    }

//    fun <T> handleMyResult(): FlowableTransformer<MyHttpResponse<T>, T> {
//
//        return FlowableTransformer<MyHttpResponse<T>, T>() {
//
//        }
//
//    }
}