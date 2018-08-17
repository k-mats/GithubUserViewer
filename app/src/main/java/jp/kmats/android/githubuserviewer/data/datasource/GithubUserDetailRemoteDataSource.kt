package jp.kmats.android.githubuserviewer.data.datasource

import io.reactivex.Observable
import jp.kmats.android.githubuserviewer.data.entity.GithubUserDetail
import jp.kmats.android.githubuserviewer.data.entity.mapper.GithubUserDetailMapper
import jp.kmats.android.githubuserviewer.domain.GithubUserDetailRepository
import okhttp3.*
import java.io.IOException

class GithubUserDetailRemoteDataSource : GithubUserDetailRepository {

    override fun getGithubUserDetail(loginId: String): Observable<GithubUserDetail> {
        return Observable.create { emitter ->
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(GithubAPI.getUserDetail(loginId))
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    emitter.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body()?.string()?.let { json ->
                        GithubUserDetailMapper.map(json)?.let { githubUserDetail ->
                            emitter.onNext(githubUserDetail)
                        }
                    }
                    emitter.onComplete()
                }
            })
        }
    }
}
