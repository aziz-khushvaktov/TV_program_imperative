package com.example.android_imperative.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_imperative.model.TVShow

@Database(entities = [TVShow::class], version = 2, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getTVShowDao(): TVShowDao

    companion object {
        private var DB_INSTANCE: AppDataBase? = null

        fun getAppDBInstance(context: Context): AppDataBase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "DB_TV_SHOWS"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!

        }
    }
}