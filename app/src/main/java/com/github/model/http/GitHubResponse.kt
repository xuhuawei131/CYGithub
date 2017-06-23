package com.github.model.http


class GitHubResponse<T> {
    var t: T? = null

    fun getResults(): T {
        return t!!
    }

    fun setResults(results: T) {
        this.t = t
    }
}
