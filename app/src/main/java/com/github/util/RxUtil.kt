package com.github.util

import android.os.Handler
import android.os.Message
import com.github.log.LogUtil
import com.github.model.http.ApiException
import com.github.model.http.GitHubResponse
import com.github.model.http.MyHttpResponse
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
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
        return FlowableTransformer<T, T> {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> handleResult(): FlowableTransformer<GitHubResponse<T>, T> {
        return FlowableTransformer<GitHubResponse<T>, T> {
            it.flatMap {
                if (it.t == null) {
                    Flowable.error(ApiException("服务器返回error"));
                } else {
                    createData(it.t)
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



        return Flowable.create(FlowableOnSubscribe<T>(){

        }, BackpressureStrategy.BUFFER)

//        return Flowable.create({ emitter ->
//            try {
//                emitter.onNext(t)
//                emitter.onComplete()
//            } catch (e: Exception) {
//                emitter.onError(e)
//            }
//        }, BackpressureStrategy.BUFFER)
    }

}