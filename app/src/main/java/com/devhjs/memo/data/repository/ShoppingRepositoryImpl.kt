package com.devhjs.memo.data.repository

import com.devhjs.memo.data.local.dao.ShoppingDao
import com.devhjs.memo.data.mapper.toDomain
import com.devhjs.memo.data.mapper.toEntity
import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingRepositoryImpl(
    private val dao: ShoppingDao
) : ShoppingRepository {

    override fun getShoppingList(): Flow<List<ShoppingItem>> {
        return dao.getShoppingList().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertShoppingItem(item: ShoppingItem) {
        dao.insertShoppingItem(item.toEntity())
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) {
        dao.deleteShoppingItem(item.toEntity())
    }

    override suspend fun updateShoppingItem(item: ShoppingItem) {
        dao.updateShoppingItem(item.toEntity())
    }

    override suspend fun deleteAllShoppingItems() {
        dao.deleteAllShoppingItems()
    }
}
