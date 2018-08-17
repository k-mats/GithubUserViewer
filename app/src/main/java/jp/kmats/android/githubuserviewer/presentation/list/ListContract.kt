package jp.kmats.android.githubuserviewer.presentation.list

import jp.kmats.android.githubuserviewer.data.entity.GithubUser

interface ListContract {

    interface View {

        fun onGithubUserListFetchedFirst(users: ArrayList<GithubUser>)

        fun onGithubUserListFetchedMore(users: ArrayList<GithubUser>)

        fun onGithubUserListFetchError(throwable: Throwable)

        fun onGithubUserListFetchComplete()

        fun onLoadingStart()

        fun onLoadinFinish()
    }

    interface Presenter {

        fun onCreate()

        fun onDestroy()

        fun onLoadMore(lastSeenNumericalId: Long)

        fun isLoading(): Boolean

        fun hasLoadedAllItems(): Boolean
    }
}
