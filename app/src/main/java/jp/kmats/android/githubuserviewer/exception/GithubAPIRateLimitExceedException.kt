package jp.kmats.android.githubuserviewer.exception

class GithubAPIRateLimitExceedException : Exception() {

    override val message: String?
        get() = "Exceeded the rate limit of Github API: 60 reqs/hour"

}
