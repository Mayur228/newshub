package com.theappmakerbuddy.newshub.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theappmakerbuddy.newshub.data.database.dao.ArticleDao
import com.theappmakerbuddy.newshub.data.database.dao.SavedArticleDao
import com.theappmakerbuddy.newshub.data.database.entity.Article
import com.theappmakerbuddy.newshub.data.database.entity.SavedArticleEntity

@Database(entities = [SavedArticleEntity::class, Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getSavedArticleDao(): SavedArticleDao
    abstract fun getArticleDao(): ArticleDao

}