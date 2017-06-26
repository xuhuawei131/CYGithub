package com.github.util

import com.github.log.LogUtil
import com.github.model.http.ApiException
import com.github.model.bean.TDogResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        return FlowableTransformer<T, T> {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 处理天狗API返回结果
     */
    fun <T> handleResult(): FlowableTransformer<TDogResponse<T>, T> {
        return FlowableTransformer<TDogResponse<T>, T> {
            it.flatMap {
                if (it.status) {
                    createData(it.tngou)
                } else {
                    Flowable.error(ApiException("服务器返回error"));
                }
            }
        }
    }

    /**
     * 生成Flowable
     * @param <T>
     * *
     * @return
    </T> */
    fun <T> createData(t: T): Flowable<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {

                LogUtil.d("abc", "createData---onError>")

                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }

}