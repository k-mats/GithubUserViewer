package jp.kmats.android.githubuserviewer.domain

import io.reactivex.Observable
import jp.kmats.android.githubuserviewer.data.entity.GithubUser

interface GithubUserRepository {

    fun getGithubUser(loginId: String): Observable<GithubUser>

    fun getGithubUserList(lastSeenNumericId: Long): Observable<List<GithubUser>>
}
