package jp.kmats.android.githubuserviewer.data.entity

import android.net.Uri
import com.squareup.moshi.Json

data class GithubUserDetail(
        @Json(name = "login") val loginId: String,
        @Json(name = "id") val numericalId: Long,
        @Json(name = "avatar_url") val avatarUrl: Uri,
        val name: String?,
        val company: String?,
        val blog: String?,
        val location: String?,
        val email: String?,
        val hireable: Boolean?,
        val bio: String?,
        @Json(name = "public_repos") val publicRepos: Int,
        @Json(name = "public_gists") val publicGists: Int,
        val followers: Int,
        val following: Int)
