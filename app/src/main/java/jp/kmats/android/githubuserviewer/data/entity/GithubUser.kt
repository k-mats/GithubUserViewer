package jp.kmats.android.githubuserviewer.data.entity

import android.net.Uri
import com.squareup.moshi.Json

data class GithubUser(
        @Json(name = "login") val loginId: String,
        @Json(name = "id") val numericalId: Long,
        @Json(name = "avatar_url") val avatarUrl: Uri)
