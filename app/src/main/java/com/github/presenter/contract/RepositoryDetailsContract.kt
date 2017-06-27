package client.yalantis.com.githubclient.flow.repository

import client.yalantis.com.githubclient.model.RepositoryDetail
import com.github.base.BasePresenter
import com.github.base.BaseView

/**
 * Created by Alexey on 07.10.2016.
 */
object RepositoryDetailsContract {

    interface View : BaseView {
        fun showRepositoryDetails(repositoryDetail: RepositoryDetail)
    }

    interface Presenter : BasePresenter<View> {
        fun loadRepositoryDetails(owner: String, name: String)
    }

}