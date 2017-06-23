package com.github.model.http

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.RepositoryDetail
import com.wingsofts.gankclient.bean.JsonResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by cuiyue on 2017/6/21.
 */
interface ApiService {

    @GET(ApiSettings.ORGANIZATION_REPOS)
    fun getOrganizationRepos(@Path(ApiSettings.PATH_ORGANIZATION) organizationName: String,
                             @Query(ApiSettings.PARAM_REPOS_TYPE) reposType: String): Flowable<MutableList<Repository>>

    @GET(ApiSettings.REPOSITORY)
    fun getRepository(@Path(ApiSettings.PATH_OWNER) owner: String,
                      @Path(ApiSettings.PATH_REPO) name: String): Flowable<RepositoryDetail>
}