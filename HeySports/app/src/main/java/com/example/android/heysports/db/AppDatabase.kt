package com.example.android.heysports.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.heysports.db.entity.ScheduleEntity
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * Created by Jihye Noh
 * Date: 2022-03-09
 */
@Database(entities = [ScheduleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "feed.db")
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    Executors.newSingleThreadExecutor().execute {
                        runBlocking {
                            // TODO : db 생성
                        }
                    }
                }
            })
            .build()
    }
}