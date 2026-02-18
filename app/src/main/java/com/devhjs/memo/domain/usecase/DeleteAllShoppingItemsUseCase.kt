package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.repository.ShoppingRepository
import javax.inject.Inject

class DeleteAllShoppingItemsUseCase @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllShoppingItems()
    }
}
