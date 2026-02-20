package com.devhjs.memo.presentation.shopping

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.usecase.AddShoppingItemUseCase
import com.devhjs.memo.domain.usecase.DeleteAllShoppingItemsUseCase
import com.devhjs.memo.domain.usecase.DeleteShoppingItemUseCase
import com.devhjs.memo.domain.usecase.GetShoppingListUseCase
import com.devhjs.memo.domain.usecase.UpdateShoppingItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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

    // 장보기 리스트 상태 (Flow를 State로 변환)
    val shoppingList = getShoppingListUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 다이얼로그 표시 여부 상태
    private val _isDialogVisible = mutableStateOf(false)
    val isDialogVisible: State<Boolean> = _isDialogVisible

    // 다이얼로그 입력 필드 상태
    private val _itemName = mutableStateOf("")
    val itemName: State<String> = _itemName

    private val _itemQuantity = mutableStateOf("")
    val itemQuantity: State<String> = _itemQuantity

    // 다이얼로그 열기/닫기
    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun hideDialog() {
        _isDialogVisible.value = false
        // 입력 필드 초기화
        _itemName.value = ""
        _itemQuantity.value = ""
    }

    // 입력 필드 업데이트
    fun onNameChange(newName: String) {
        _itemName.value = newName
    }

    fun onQuantityChange(newQuantity: String) {
        _itemQuantity.value = newQuantity
    }

    // 아이템 추가
    fun addItem() {
        if (_itemName.value.isNotBlank()) {
            viewModelScope.launch {
                addShoppingItemUseCase(
                    ShoppingItem(
                        name = _itemName.value,
                        quantity = _itemQuantity.value,
                        isChecked = false
                    )
                )
                hideDialog()
            }
        }
    }

    // 아이템 삭제
    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch {
            deleteShoppingItemUseCase(item)
        }
    }

    // 전체 삭제
    fun deleteAllItems() {
        viewModelScope.launch {
            deleteAllShoppingItemsUseCase()
        }
    }

    // 체크 상태 토글
    fun toggleItemChecked(item: ShoppingItem) {
        viewModelScope.launch {
            updateShoppingItemUseCase(item.copy(isChecked = !item.isChecked))
        }
    }
}
