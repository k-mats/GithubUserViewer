package jp.kmats.android.githubuserviewer.data.datasource

import io.reactivex.Observable
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import jp.kmats.android.githubuserviewer.data.entity.mapper.GithubUserMapper
import jp.kmats.android.githubuserviewer.domain.GithubUserRepository
import okhttp3.*
import java.io.IOException

class GithubUserRemoteDataSource : GithubUserRepository {

    override fun getGithubUser(loginId: String): Observable<GithubUser> {
        return Observable.create { emitter ->
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(GithubAPI.getUser(loginId))
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    emitter.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body()?.string()?.let { json ->
                        GithubUserMapper.mapUser(json)?.let { githubUser ->
                            emitter.onNext(githubUser)
                        }
                    }
                    emitter.onComplete()
                }
            })
        }
    }

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
                        GithubUserMapper.mapUserList(json)?.let { githubUserList ->
                            emitter.onNext(githubUserList)
                        }
                    }
                    emitter.onComplete()
                }
            })
        }
    }
}
