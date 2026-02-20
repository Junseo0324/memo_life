package com.devhjs.memo.presentation.shopping

sealed interface ShoppingEvent {
    data class ShowSnackbar(val message: String) : ShoppingEvent
    data object NavigateBack : ShoppingEvent
}
