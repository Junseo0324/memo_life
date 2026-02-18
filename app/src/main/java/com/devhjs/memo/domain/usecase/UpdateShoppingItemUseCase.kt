package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import javax.inject.Inject

class UpdateShoppingItemUseCase @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(item: ShoppingItem) {
        repository.updateShoppingItem(item)
    }
}
