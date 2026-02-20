package com.devhjs.memo.presentation.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.usecase.AddShoppingItemUseCase
import com.devhjs.memo.domain.usecase.DeleteAllShoppingItemsUseCase
import com.devhjs.memo.domain.usecase.DeleteShoppingItemUseCase
import com.devhjs.memo.domain.usecase.GetShoppingListUseCase
import com.devhjs.memo.domain.usecase.UpdateShoppingItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    getShoppingListUseCase: GetShoppingListUseCase,
    private val addShoppingItemUseCase: AddShoppingItemUseCase,
    private val deleteShoppingItemUseCase: DeleteShoppingItemUseCase,
    private val updateShoppingItemUseCase: UpdateShoppingItemUseCase,
    private val deleteAllShoppingItemsUseCase: DeleteAllShoppingItemsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingState())
    val state: StateFlow<ShoppingState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<ShoppingEvent>()
    val event: SharedFlow<ShoppingEvent> = _event.asSharedFlow()

    init {
        getShoppingListUseCase()
            .onEach { list ->
                _state.update { 
                    it.copy(shoppingList = list) 
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ShoppingAction) {
        when (action) {
            is ShoppingAction.ShowDialog -> {
                _state.update { 
                    it.copy(isDialogVisible = true) 
                }
            }
            is ShoppingAction.HideDialog -> {
                _state.update { 
                    it.copy(isDialogVisible = false) 
                }
            }
            is ShoppingAction.SaveShoppingItem -> {
                if (action.name.isNotBlank()) {
                    viewModelScope.launch {
                        addShoppingItemUseCase(
                            ShoppingItem(
                                name = action.name,
                                quantity = action.quantity,
                                isChecked = false
                            )
                        )
                        _event.emit(ShoppingEvent.ShowSnackbar("새로운 항목이 추가되었습니다."))
                        _state.update { 
                            it.copy(isDialogVisible = false) 
                        }
                    }
                }
            }
            is ShoppingAction.DeleteShoppingItem -> {
                viewModelScope.launch {
                    deleteShoppingItemUseCase(action.item)
                    _event.emit(ShoppingEvent.ShowSnackbar("항목이 삭제되었습니다."))
                }
            }
            is ShoppingAction.DeleteAllShoppingItems -> {
                viewModelScope.launch {
                    deleteAllShoppingItemsUseCase()
                    _event.emit(ShoppingEvent.ShowSnackbar("모든 항목이 삭제되었습니다."))
                }
            }
            is ShoppingAction.ToggleItemChecked -> {
                viewModelScope.launch {
                    updateShoppingItemUseCase(action.item.copy(isChecked = !action.item.isChecked))
                }
            }
            is ShoppingAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(ShoppingEvent.NavigateBack)
                }
            }
        }
    }
}
