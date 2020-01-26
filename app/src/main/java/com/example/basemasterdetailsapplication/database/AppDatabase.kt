package com.example.basemasterdetailsapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basemasterdetailsapplication.database.models.DatabaseDummyData

@Database(entities = [DatabaseDummyData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val dataDao: DataDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this)
            {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "data_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}