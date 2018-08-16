package jp.kmats.android.githubuserviewer.data.datasource

object GithubAPI {

    private const val USERS_URL = "https://api.github.com/users"

    fun getUser(loginId: String) = "$USERS_URL/$loginId"

    fun getUserList(lastSeenNumericId: Long) = "$USERS_URL?since=$lastSeenNumericId"
}
