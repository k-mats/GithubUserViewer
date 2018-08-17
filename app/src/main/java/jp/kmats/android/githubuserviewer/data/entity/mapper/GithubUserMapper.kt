package jp.kmats.android.githubuserviewer.data.entity.mapper

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import jp.kmats.android.githubuserviewer.data.entity.moshi.MoshiSingleton.moshi

object GithubUserMapper {

    fun mapUser(json: String): GithubUser? {
        return moshi.adapter(GithubUser::class.java).fromJson(json)
    }

    fun mapUserList(json: String): ArrayList<GithubUser>? {
        val type = Types.newParameterizedType(List::class.java, GithubUser::class.java)
        val adapter: JsonAdapter<ArrayList<GithubUser>> = moshi.adapter(type)
        return adapter.fromJson(json)
    }
}
