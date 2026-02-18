package com.devhjs.memo.domain.repository

import com.devhjs.memo.domain.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
    
    fun getShoppingList(): Flow<List<ShoppingItem>>
    
    suspend fun insertShoppingItem(item: ShoppingItem)
    
    suspend fun deleteShoppingItem(item: ShoppingItem)
    
    suspend fun updateShoppingItem(item: ShoppingItem)
    
    suspend fun deleteAllShoppingItems()
}
