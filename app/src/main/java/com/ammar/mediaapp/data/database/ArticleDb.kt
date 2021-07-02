package com.ammar.mediaapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ammar.mediaapp.dao.ArticleDao
import com.ammar.mediaapp.data.model.Article
import com.ammar.mediaapp.util.Converter


@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDb : RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao

    companion object {
        @Volatile
        private var instance : ArticleDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDb::class.java,
                "articleDB"
            ).build()
    }
}

