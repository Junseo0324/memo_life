package com.devhjs.memo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devhjs.memo.data.local.dao.InformationDao
import com.devhjs.memo.data.local.dao.RecipeDao
import com.devhjs.memo.data.local.dao.ShoppingDao
import com.devhjs.memo.data.local.entity.InformationEntity
import com.devhjs.memo.data.local.entity.RecipeEntity
import com.devhjs.memo.data.local.entity.ShoppingEntity

@Database(
    entities = [
        ShoppingEntity::class, 
        InformationEntity::class,
        RecipeEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeConverters::class)
abstract class MemoDatabase : RoomDatabase() {

    abstract val shoppingDao: ShoppingDao
    abstract val informationDao: InformationDao
    abstract val recipeDao: RecipeDao

    companion object {
        const val DATABASE_NAME = "memo_db"

        @Volatile
        private var INSTANCE: MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
