package com.devhjs.memo.data.local

import androidx.room.*
import com.devhjs.memo.data.local.entity.ShoppingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM shopping_items ORDER BY id DESC")
    fun getShoppingList(): Flow<List<ShoppingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(item: ShoppingEntity)

    @Delete
    suspend fun deleteShoppingItem(item: ShoppingEntity)

    @Update
    suspend fun updateShoppingItem(item: ShoppingEntity)

    @Query("DELETE FROM shopping_items")
    suspend fun deleteAllShoppingItems()
}
