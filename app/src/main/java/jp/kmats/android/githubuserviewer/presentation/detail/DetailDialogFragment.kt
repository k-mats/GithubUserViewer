package jp.kmats.android.githubuserviewer.presentation.detail

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.kmats.android.githubuserviewer.R
import jp.kmats.android.githubuserviewer.data.entity.GithubUserDetail
import kotlinx.android.synthetic.main.fragment_detail_dialog.*

class DetailDialogFragment : DialogFragment() {

    private lateinit var userDetail: GithubUserDetail

    companion object {
        fun newInstance(userDetail: GithubUserDetail): DetailDialogFragment {
            val fragment = DetailDialogFragment()
            fragment.userDetail = userDetail
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView(view)
    }

    private fun setupView(view: View) {
        val glideOptions = RequestOptions().circleCrop().placeholder(R.drawable.avatar_placeholder)
        Glide.with(view).load(userDetail.avatarUrl).apply(glideOptions).into(avatarImageView)

        nameTextView.text = userDetail.name ?: view.resources.getString(R.string.no_name)
        companyTextView.text = userDetail.company ?: view.resources.getString(R.string.company_not_registered)
        loginIdTextView.text = userDetail.loginId
        locationTextView.text = userDetail.location ?: view.resources.getString(R.string.location_not_registered)
        emailTextView.text = userDetail.email ?: view.resources.getString(R.string.email_not_registered)

        val followStatusString = view.resources.getString(R.string.follow_status)
        followStatusTextView.text = String.format(followStatusString, userDetail.following, userDetail.followers)

        val repoStatusString = view.resources.getString(R.string.repo_status)
        repoStatusTextView.text = String.format(repoStatusString, userDetail.publicRepos, userDetail.publicGists)

        if (userDetail.hireable == true) {
            hireableTextView.text = view.resources.getString(R.string.hireable)
        } else {
            hireableTextView.text = view.resources.getString(R.string.not_hireable)
        }

        dismissButton.setOnClickListener { dismiss() }
    }
}
