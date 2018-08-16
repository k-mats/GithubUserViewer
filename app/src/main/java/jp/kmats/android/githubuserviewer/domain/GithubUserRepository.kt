package jp.kmats.android.githubuserviewer.domain

import io.reactivex.Observable
import jp.kmats.android.githubuserviewer.data.entity.GithubUser

interface GithubUserRepository {

    fun getGithubUser(loginId: String): Observable<GithubUser>

    /**
     * The "last seen numeric ID 0" equals to no `since` parameter.
     * Thus both urls below returns the same json.
     * - https://api.github.com/users
     * - https://api.github.com/users?since=0
     */
    fun getGithubUserList(lastSeenNumericId: Long = 0): Observable<List<GithubUser>>
}
