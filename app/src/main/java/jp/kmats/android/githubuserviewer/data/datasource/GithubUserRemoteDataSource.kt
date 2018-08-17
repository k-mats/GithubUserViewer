package jp.kmats.android.githubuserviewer.data.datasource

import io.reactivex.Observable
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import jp.kmats.android.githubuserviewer.data.entity.mapper.GithubUserMapper
import jp.kmats.android.githubuserviewer.domain.GithubUserRepository
import jp.kmats.android.githubuserviewer.exception.GithubAPIRateLimitExceedException
import okhttp3.*
import java.io.IOException

class GithubUserRemoteDataSource : GithubUserRepository {

    override fun getGithubUserList(lastSeenNumericId: Long): Observable<ArrayList<GithubUser>> {
        return Observable.create { emitter ->
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(GithubAPI.getUserList(lastSeenNumericId))
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    emitter.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body()?.string()?.let { json ->
                        if (GithubAPI.isRateLimitExceeded(json)) {
                            emitter.onError(GithubAPIRateLimitExceedException())
                        } else {
                            GithubUserMapper.mapUserList(json)?.let { githubUserList ->
                                emitter.onNext(githubUserList)
                            }
                        }
                    }
                    emitter.onComplete()
                }
            })
        }
    }
}
