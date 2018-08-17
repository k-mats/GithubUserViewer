package jp.kmats.android.githubuserviewer.data.datasource

object GithubAPI {

    private const val USERS_URL = "https://api.github.com/users"

    fun getUserDetail(loginId: String) = "$USERS_URL/$loginId"

    fun getUserList(lastSeenNumericId: Long) = "$USERS_URL?since=$lastSeenNumericId"

    fun isRateLimitExceeded(json: String) = json.contains("API rate limit exceeded")
}
