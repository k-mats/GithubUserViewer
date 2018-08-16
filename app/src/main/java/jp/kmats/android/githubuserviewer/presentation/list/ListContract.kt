package jp.kmats.android.githubuserviewer.presentation.list

import jp.kmats.android.githubuserviewer.data.entity.GithubUser

interface ListContract {

    interface View {

        fun onGithubUserListFetched(users: List<GithubUser>)
    }

    interface Presenter {

        fun onCreate()

        fun onDestroy()
    }
}
