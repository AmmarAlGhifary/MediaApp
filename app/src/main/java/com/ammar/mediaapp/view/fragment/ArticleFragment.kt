package com.ammar.mediaapp.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ammar.mediaapp.R
import com.ammar.mediaapp.view.activity.MainActivity
import com.ammar.mediaapp.view.viewmodel.NewsViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*
import java.text.SimpleDateFormat
import java.util.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val articleFragmentArgs: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).vm
        val article = articleFragmentArgs.article

        if (article.urlToImage != null) {
            Glide.with(this).load(article.urlToImage).into(imageView)
        }

        tvSource.text = article.source.name
        tvDate.text = article.publishedAt?.let { unixTime(it) }
        tvTitle.text = article.title
        tvAuthor.text = article.author
        tvDescription.text = article.description
        tvContent.text = article.content

        toolbar.setNavigationOnClickListener { (activity as MainActivity).onBackPressed() }

        webView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(article.url)
            startActivity(intent)
        }

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Saved article", Snackbar.LENGTH_SHORT).show()
        }

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing news from Smart News App")
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                article.title + "\n\n" + article.description + "\n\n" + article.url
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun unixTime(timex: String): String? {
        val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:")
        val date = zonedFormat.parse(timex)
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm")
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

}