package com.example.tastevin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tastevin.database.bookmark.Bookmark
import com.example.tastevin.database.bookmark.BookmarkDao
import com.example.tastevin.database.wine.Wine
import com.example.tastevin.database.wine.WineDao

@Database(entities = [Wine::class, Bookmark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wineDao(): WineDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}