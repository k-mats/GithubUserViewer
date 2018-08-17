package jp.kmats.android.githubuserviewer.presentation.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.paginate.Paginate
import jp.kmats.android.githubuserviewer.R
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import kotlinx.android.synthetic.main.activity_list.*
import timber.log.Timber


class ListActivity : AppCompatActivity(), ListContract.View, Paginate.Callbacks {

    private val presenter: ListContract.Presenter = ListPresenter(this)
    private var paginate: Paginate? = null
    private var adapter: GithubUserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        paginate?.unbind()
        adapter?.destroy()
        super.onDestroy()
    }

    override fun onGithubUserListFetchedFirst(users: ArrayList<GithubUser>) {
        setupRecyclerView(users)
    }

    override fun onGithubUserListFetchedMore(users: ArrayList<GithubUser>) {
        showMoreUsers(users)
    }

    override fun onGithubUserListFetchError(throwable: Throwable) {
        Timber.e(throwable)
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onGithubUserListFetchComplete() {
    }

    override fun onLoadMore() {
        val lastSeenNumericalId = adapter?.getLastUsersNumericalId() ?: 0
        presenter.onLoadMore(lastSeenNumericalId)
    }

    override fun isLoading(): Boolean = presenter.isLoading()

    override fun hasLoadedAllItems(): Boolean = presenter.hasLoadedAllItems()

    private fun setupRecyclerView(users: ArrayList<GithubUser>) {
        githubUserRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GithubUserAdapter(users, this)
        githubUserRecyclerView.adapter = adapter

        paginate?.unbind()
        paginate = Paginate.with(githubUserRecyclerView, this)
                .addLoadingListItem(true)
                .build()
    }

    private fun showMoreUsers(users: ArrayList<GithubUser>) {
        adapter?.showMoreUsers(users)
    }
}
