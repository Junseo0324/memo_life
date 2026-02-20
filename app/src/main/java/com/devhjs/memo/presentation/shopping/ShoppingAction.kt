package com.devhjs.memo.presentation.shopping

import com.devhjs.memo.domain.model.ShoppingItem

sealed interface ShoppingAction {
    data object ShowDialog : ShoppingAction
    data object HideDialog : ShoppingAction
    data class SaveShoppingItem(val name: String, val quantity: String) : ShoppingAction
    data class DeleteShoppingItem(val item: ShoppingItem) : ShoppingAction
    data object DeleteAllShoppingItems : ShoppingAction
    data class ToggleItemChecked(val item: ShoppingItem) : ShoppingAction
    data object OnBackClick : ShoppingAction
}
