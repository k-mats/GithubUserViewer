package jp.kmats.android.githubuserviewer.presentation.list

import jp.kmats.android.githubuserviewer.data.entity.GithubUserDetail

interface GithubUserAdapterContract {

    interface View {

        fun onGithubUserDetailFetched(userDetail: GithubUserDetail)

        fun onGithubUserDetailFetchError(throwable: Throwable)

        fun onGithubUserDetailFetchComplete()
    }

    interface Presenter {

        fun destroy()

        fun onItemViewClick(loginId: String)
    }
}
