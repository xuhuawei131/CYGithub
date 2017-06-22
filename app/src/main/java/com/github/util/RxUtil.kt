package com.github.util

import android.os.Handler
import android.os.Message
import com.github.log.LogUtil
import com.github.model.http.ApiException
import com.github.model.http.GitHubResponse
import com.github.model.http.MyHttpResponse
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
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
        var flowableTransformer = object : FlowableTransformer<T, T> {
            override fun apply(upstream: Flowable<T>?): Publisher<T> {
                return upstream?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())!!
            }
        }
        return flowableTransformer

    }


//    public static <T> FlowableTransformer<GoldHttpResponse<T>, T> handleGoldResult() {   //compose判断结果
//        return new FlowableTransformer<GoldHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<GoldHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<GoldHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(GoldHttpResponse<T> tGoldHttpResponse) {
//                        if(tGoldHttpResponse.getResults() != null) {
//                            return createData(tGoldHttpResponse.getResults());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }


}