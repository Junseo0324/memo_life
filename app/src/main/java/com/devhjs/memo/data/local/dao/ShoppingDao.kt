package com.devhjs.memo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devhjs.memo.data.local.entity.ShoppingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM shopping_items ORDER BY id DESC")
    fun getShoppingList(): Flow<List<ShoppingEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertShoppingItem(item: ShoppingEntity)

    @Delete
    suspend fun deleteShoppingItem(item: ShoppingEntity)

    @Update
    suspend fun updateShoppingItem(item: ShoppingEntity)

    @Query("DELETE FROM shopping_items")
    suspend fun deleteAllShoppingItems()
}