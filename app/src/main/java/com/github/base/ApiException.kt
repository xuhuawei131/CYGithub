package com.github.base

/**
 * Created by cuiyue on 2017/6/22.
 */
class ApiException : Exception {

    private var code: Int = 0

    constructor(msg: String) {
    }

    constructor(msg: String, code: Int) {
        this.code = code
    }

    fun getCode(): Int {
        return code
    }


}