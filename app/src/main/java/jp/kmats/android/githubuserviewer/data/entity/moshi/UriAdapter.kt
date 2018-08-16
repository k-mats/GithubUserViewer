package jp.kmats.android.githubuserviewer.data.entity.moshi

import android.net.Uri
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class UriAdapter {

    @Suppress("unused")
    @ToJson
    fun toJson(uri: Uri): String {
        return uri.toString()
    }

    @Suppress("unused")
    @FromJson
    fun fromJson(uriJson: String): Uri {
        return Uri.parse(uriJson)
    }
}
