package jp.kmats.android.githubuserviewer.presentation.list

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.kmats.android.githubuserviewer.R
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import jp.kmats.android.githubuserviewer.data.entity.GithubUserDetail
import jp.kmats.android.githubuserviewer.presentation.detail.DetailDialogFragment
import kotlinx.android.synthetic.main.view_githubuser_card.view.*
import timber.log.Timber

class GithubUserAdapter(private val users: ArrayList<GithubUser>, private val activity: AppCompatActivity)
    : RecyclerView.Adapter<GithubUserAdapter.CardViewHolder>(), GithubUserAdapterContract.View {

    private val presenter: GithubUserAdapterContract.Presenter = GithubUserAdapterPresenter(this)

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        val cardView = layoutInflater.inflate(R.layout.view_githubuser_card, parent, false)
        return CardViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val user = users[position]

        val glideOptions = RequestOptions().circleCrop().placeholder(R.drawable.avatar_placeholder)
        Glide.with(activity).load(user.avatarUrl).apply(glideOptions).into(holder.avatarImageView)

        holder.loginIdTextView.text = user.loginId

        val idText = activity.resources.getString(R.string.cardview_id_text)
        holder.numericalIdTextView.text = String.format(idText, user.numericalId)

        holder.itemView.setOnClickListener { presenter.onItemViewClick(user.loginId) }
    }

    override fun onGithubUserDetailFetched(userDetail: GithubUserDetail) {
        val ft = activity.supportFragmentManager.beginTransaction()
        val fragment = DetailDialogFragment.newInstance(userDetail)
        fragment.show(ft, DetailDialogFragment::class.java.toString())
    }

    override fun onGithubUserDetailFetchError(throwable: Throwable) {
        Timber.e(throwable)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onGithubUserDetailFetchComplete() {
    }

    fun showMoreUsers(additionalUsers: ArrayList<GithubUser>) {
        val positionStart = users.size
        users.addAll(additionalUsers)
        notifyItemRangeInserted(positionStart, additionalUsers.size)
    }

    fun getLastUsersNumericalId(): Long {
        return users.last().numericalId
    }

    fun destroy() {
        presenter.destroy()
    }

    class CardViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView) {
        val avatarImageView: ImageView = cardView.avatarImageView
        val loginIdTextView: TextView = cardView.loginIdTextView
        val numericalIdTextView: TextView = cardView.numericalIdTextView
    }
}
