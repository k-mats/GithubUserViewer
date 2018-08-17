package jp.kmats.android.githubuserviewer.presentation.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import jp.kmats.android.githubuserviewer.data.datasource.GithubUserDetailRemoteDataSource
import jp.kmats.android.githubuserviewer.domain.GithubUserDetailRepository

class GithubUserAdapterPresenter(val view: GithubUserAdapterContract.View) : GithubUserAdapterContract.Presenter {

    private val repository: GithubUserDetailRepository = GithubUserDetailRemoteDataSource()
    private val compositeDisposable = CompositeDisposable()

    override fun destroy() {
        compositeDisposable.dispose()
    }

    override fun onItemViewClick(loginId: String) {
        repository.getGithubUserDetail(loginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userDetail ->
                    view.onGithubUserDetailFetched(userDetail)
                }, { throwable ->
                    view.onGithubUserDetailFetchError(throwable)
                }, {
                    view.onGithubUserDetailFetchComplete()
                }).addTo(compositeDisposable)
    }
}
