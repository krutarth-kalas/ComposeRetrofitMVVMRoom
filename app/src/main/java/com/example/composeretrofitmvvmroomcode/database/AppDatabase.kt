package com.example.composeretrofitmvvmroomcode.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase

@TypeConverters(
    ConverterList::class
)

@Database(entities = [ProductEntity::class] , version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao


    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "trainingApp"
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            Log.d("UPI_DATABASE", "Database created")
                            super.onCreate(db)
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            Log.d("UPI_DATABASE", "Database opened")
                            super.onOpen(db)
                        }
                    })
                        .enableMultiInstanceInvalidation()
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}