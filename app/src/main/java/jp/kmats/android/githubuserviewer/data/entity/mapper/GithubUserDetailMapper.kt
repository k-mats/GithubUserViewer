package jp.kmats.android.githubuserviewer.data.entity.mapper

import jp.kmats.android.githubuserviewer.data.entity.GithubUserDetail
import jp.kmats.android.githubuserviewer.data.entity.moshi.MoshiSingleton.moshi

object GithubUserDetailMapper {

    fun map(json: String): GithubUserDetail? {
        return moshi.adapter(GithubUserDetail::class.java).fromJson(json)
    }
}
