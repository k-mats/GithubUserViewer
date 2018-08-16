package jp.kmats.android.githubuserviewer.presentation.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import jp.kmats.android.githubuserviewer.R
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), ListContract.View {

    private val presenter: ListContract.Presenter = ListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onGithubUserListFetched(users: List<GithubUser>) {
        setupRecyclerView(users)
    }

    private fun setupRecyclerView(users: List<GithubUser>) {
        githubUserRecyclerView.layoutManager = LinearLayoutManager(this)
        githubUserRecyclerView.adapter = GithubUserAdapter(users, this)
    }
}
