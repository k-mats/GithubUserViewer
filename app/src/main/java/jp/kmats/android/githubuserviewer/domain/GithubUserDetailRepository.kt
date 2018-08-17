package jp.kmats.android.githubuserviewer.domain

import io.reactivex.Observable
import jp.kmats.android.githubuserviewer.data.entity.GithubUserDetail

interface GithubUserDetailRepository {

    fun getGithubUserDetail(loginId: String): Observable<GithubUserDetail>
}
