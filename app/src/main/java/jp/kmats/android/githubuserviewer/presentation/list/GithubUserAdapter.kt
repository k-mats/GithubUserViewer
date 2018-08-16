package jp.kmats.android.githubuserviewer.presentation.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import jp.kmats.android.githubuserviewer.R
import jp.kmats.android.githubuserviewer.data.entity.GithubUser
import kotlinx.android.synthetic.main.view_githubuser_card.view.*

class GithubUserAdapter(private val users: List<GithubUser>, private val context: Context) : RecyclerView.Adapter<GithubUserAdapter.CardViewHolder>() {

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cardView = layoutInflater.inflate(R.layout.view_githubuser_card, parent, false)
        return CardViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val user = users[position]
        Glide.with(context).load(user.avatarUrl).into(holder.avatarImageView)
    }

    class CardViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView) {
        val avatarImageView: ImageView = cardView.avatarImageView
    }
}
