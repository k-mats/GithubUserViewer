package jp.kmats.android.githubuserviewer.presentation.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.kmats.android.githubuserviewer.R
import jp.kmats.android.githubuserviewer.data.datasource.GithubUserRemoteDataSource
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import jp.kmats.android.githubuserviewer.domain.GithubUserRepository
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val repo: GithubUserRepository = GithubUserRemoteDataSource()
        repo.getGithubUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setupRecyclerView)
    }

    private fun setupRecyclerView(users: List<GithubUser>) {
        githubUserRecyclerView.layoutManager = LinearLayoutManager(this)
        githubUserRecyclerView.adapter = GithubUserAdapter(users, this)
    }
}
