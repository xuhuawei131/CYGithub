package com.github.model.http

/**
 * Created by cuiyue on 2017/6/22.
 */
class MyHttpResponse<T> {

    private var code: Int = 0
    private var message: String = ""
    private var data: T = null!!

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getMessage(): String {
        return message
    }

    fun setData(data: T) {
        this.data = data
    }

    fun getData(): T {
        return data
    }

    fun setMessage(message: String) {
        this.message = message
    }
}