package jp.kmats.android.githubuserviewer.presentation.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import jp.kmats.android.githubuserviewer.data.datasource.GithubUserRemoteDataSource
import jp.kmats.android.githubuserviewer.domain.GithubUserRepository

class ListPresenter(val view: ListContract.View) : ListContract.Presenter {

    private val repository: GithubUserRepository = GithubUserRemoteDataSource()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        repository.getGithubUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onGithubUserListFetched)
                .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
