
package com.example.android.challenge.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.android.challenge.model.Content


@Database(
        entities = [Content::class],
        version = 1,
        exportSchema = false
)
abstract class ContentDatabase : RoomDatabase() {

    abstract fun contentsDao(): ContentDao

    companion object {

        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        ContentDatabase::class.java, "content.db")
                        .build()
    }
}
