package com.example.cookingrecipes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private val name = "users.db"

        fun getInstance(context: Context): AppDatabase {
            db?.let { return it }
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                name
            ).build()
            db = instance
            return instance
        }
    }

    abstract fun getUserDao(): RecipeDao
}