package com.souzaemerson.aluramvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.souzaemerson.aluramvvm.data.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun bookDao(): DatabaseDAO

    companion object{
        @Volatile
        private var getInstance: AppDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //include
            }
        }

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = getInstance
            tempInstance?.let {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                AppDatabase::class.java,
                "book.db")
                    .addMigrations(MIGRATION_1_2)
                    .build()

                getInstance = instance
                return instance
            }
        }
    }
}