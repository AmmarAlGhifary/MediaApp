package com.ammar.mediaapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ammar.mediaapp.R
import com.ammar.mediaapp.data.database.ArticleDb
import com.ammar.mediaapp.data.repository.NewsRepository
import com.ammar.mediaapp.view.viewmodel.NewsViewModel
import com.ammar.mediaapp.view.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var vm: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nr = NewsRepository(ArticleDb(this))
        val vmpf = ViewModelFactory(application, nr)
        vm = ViewModelProvider(this, vmpf).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}