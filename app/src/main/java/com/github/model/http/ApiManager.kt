package com.github.model.http


import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.RepositoryDetail
import client.yalantis.com.githubclient.model.TDog
import com.github.BuildConfig
import com.github.log.okHttpLog.HttpLoggingInterceptorM
import com.github.log.okHttpLog.LogInterceptor
import com.github.model.bean.TDogResponse
import com.github.util.Utils
import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by cuiyue on 2017/6/21.
 */
object ApiManager {

    private val SERVER: String = "https://api.github.com/"

//    private val SERVER: String = "http://www.tngou.net/api/"

    private val HTTP_LOG_TAG: String = "abc"

    private lateinit var mApiService: ApiService

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {


        var builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptorM(LogInterceptor(HTTP_LOG_TAG))
            interceptor.setLevel(HttpLoggingInterceptorM.Level.BODY)
            builder.addInterceptor(interceptor)
        }

        val cachePath = Utils.getCachePath()
        var cacheFile = File(cachePath)
        val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong())
        val cacheInterceptor = Interceptor { chain ->
            var request = chain.request()
            if (!Utils.isNetworkConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (Utils.isNetworkConnected()) {
                val maxAge = 0
                // 有网络时, 不缓存, 最大保存时长为0
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build()
            } else {
                // 无网络时，设置超时为4周
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build()
            }
            response
        }
//        val apikey = Interceptor { chain ->
//            var request = chain.request()
//            request = request.newBuilder()
//                    .addHeader("apikey", "header")
//                    .build()
//            chain.proceed(request)
//        }
//        //设置统一的请求头部参数
//        builder.addInterceptor(apikey)
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor)
        builder.addInterceptor(cacheInterceptor)
        builder.cache(cache)
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        //错误重连
        builder.retryOnConnectionFailure(true)
        var okHttpClient = builder.build()
        return Retrofit.Builder().baseUrl(SERVER)
                .client(okHttpClient)
                .addConverterFactory(createGsonConverter())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun initServices(retrofit: Retrofit) {
        mApiService = retrofit.create(ApiService::class.java)
    }

    private fun createGsonConverter(): GsonConverterFactory {
        val builder = GsonBuilder().serializeNulls()
        return GsonConverterFactory.create(builder.create())
    }

    fun loadOrganizationRepos(organizationName: String, reposType: String, per_page: String): Flowable<MutableList<Repository>> {
        return mApiService.getOrganizationRepos(organizationName, reposType, per_page)
    }

    fun loadRepository(owner: String, name: String): Flowable<RepositoryDetail> {
        return mApiService.getRepository(owner, name)
    }

    fun loadTDog(): Flowable<TDogResponse<MutableList<TDog>>> {
        return mApiService.getTDog()
    }

}