package com.ammar.mediaapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ammar.mediaapp.R
import com.ammar.mediaapp.data.model.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        if (article.description != null) {
            holder.itemView.apply {
                if (article.urlToImage != null) {
                    Glide.with(this).load(article.urlToImage).into(ivArticleImage)
                }
                tvSource.text = article.source.name
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt?.let { unixTime(it) }
                setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    private fun unixTime(timex: String): String? {
        val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:")
        val date = zonedFormat.parse(timex)
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm")
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
