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
    private var loading = false

    override fun onCreate() {
        repository.getGithubUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    loading = true
                    view.onLoadingStart()
                }
                .doFinally {
                    loading = false
                    view.onLoadinFinish()
                }
                .subscribe({ users ->
                    view.onGithubUserListFetchedFirst(users)
                }, { throwable ->
                    view.onGithubUserListFetchError(throwable)
                }, {
                    view.onGithubUserListFetchComplete()
                }).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    override fun onLoadMore(lastSeenNumericalId: Long) {
        repository.getGithubUserList(lastSeenNumericalId)
                .doOnSubscribe { loading = true }
                .doFinally { loading = false }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    view.onGithubUserListFetchedMore(users)
                }, { throwable ->
                    view.onGithubUserListFetchError(throwable)
                }, {
                    view.onGithubUserListFetchComplete()
                }).addTo(compositeDisposable)
    }

    override fun isLoading(): Boolean = loading

    // To prevent known issue; The pagination is executed twice.
    // cf. https://github.com/MarkoMilos/Paginate/issues/17
    override fun hasLoadedAllItems(): Boolean = loading
}
