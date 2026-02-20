package com.devhjs.memo.presentation.shopping

import androidx.compose.runtime.Immutable
import com.devhjs.memo.domain.model.ShoppingItem

@Immutable
data class ShoppingState(
    val isDialogVisible: Boolean = false,
    val shoppingList: List<ShoppingItem> = emptyList()
)
