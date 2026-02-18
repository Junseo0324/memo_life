package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(
    private val repository: ShoppingRepository
) {
    operator fun invoke(): Flow<List<ShoppingItem>> {
        return repository.getShoppingList()
    }
}
