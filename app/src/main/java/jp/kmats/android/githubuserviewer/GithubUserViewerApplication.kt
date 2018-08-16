package jp.kmats.android.githubuserviewer

import android.app.Application
import timber.log.Timber

class GithubUserViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {
        initializeTimber()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
