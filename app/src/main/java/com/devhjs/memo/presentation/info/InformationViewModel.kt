package com.devhjs.memo.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.usecase.AddInformationItemUseCase
import com.devhjs.memo.domain.usecase.DeleteInformationItemUseCase
import com.devhjs.memo.domain.usecase.FilterInformationListUseCase
import com.devhjs.memo.domain.usecase.GetInformationListUseCase
import com.devhjs.memo.domain.usecase.UpdateInformationItemUseCase
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
class InformationViewModel @Inject constructor(
    getInformationListUseCase: GetInformationListUseCase,
    private val addInformationItemUseCase: AddInformationItemUseCase,
    private val updateInformationItemUseCase: UpdateInformationItemUseCase,
    private val deleteInformationItemUseCase: DeleteInformationItemUseCase,
    private val filterInformationListUseCase: FilterInformationListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(InformationState())
    val state: StateFlow<InformationState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<InformationEvent>()
    val event: SharedFlow<InformationEvent> = _event.asSharedFlow()

    init {
        getInformationListUseCase()
            .onEach { list ->
                _state.update { 
                    it.copy(
                        infoList = list,
                        filteredInfoList = filterInformationListUseCase(list, it.searchQuery)
                    ) 
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: InformationAction) {
        when (action) {
            is InformationAction.UpdateSearchQuery -> {
                _state.update { 
                    it.copy(
                        searchQuery = action.query,
                        filteredInfoList = filterInformationListUseCase(it.infoList, action.query)
                    ) 
                }
            }
            is InformationAction.ShowDialog -> {
                _state.update { 
                    it.copy(
                        isDialogVisible = true,
                        selectedItemForEdit = null
                    ) 
                }
                clearForm()
            }
            is InformationAction.ShowEditDialog -> {
                _state.update { 
                    it.copy(
                        isDialogVisible = true,
                        selectedItemForEdit = action.item,
                        siteName = action.item.siteName,
                        userId = action.item.userId,
                        userPw = action.item.userPw,
                        memo = action.item.memo
                    )
                }
            }
            is InformationAction.HideDialog -> {
                _state.update { 
                    it.copy(
                        isDialogVisible = false,
                        selectedItemForEdit = null
                    ) 
                }
                clearForm()
            }
            is InformationAction.UpdateSiteName -> {
                _state.update { it.copy(siteName = action.siteName) }
            }
            is InformationAction.UpdateUserId -> {
                _state.update { it.copy(userId = action.userId) }
            }
            is InformationAction.UpdateUserPw -> {
                _state.update { it.copy(userPw = action.userPw) }
            }
            is InformationAction.UpdateMemo -> {
                _state.update { it.copy(memo = action.memo) }
            }
            is InformationAction.ClearForm -> {
                clearForm()
            }
            is InformationAction.AddInformation -> {
                viewModelScope.launch {
                    val currentState = _state.value
                    
                    if (currentState.selectedItemForEdit != null) {
                        // 수정 모드
                        val updatedItem = currentState.selectedItemForEdit.copy(
                            siteName = currentState.siteName,
                            userId = currentState.userId,
                            userPw = currentState.userPw,
                            memo = currentState.memo
                        )
                        updateInformationItemUseCase(updatedItem)
                        _event.emit(InformationEvent.ShowSnackbar("정보가 수정되었습니다."))
                    } else {
                        // 추가 모드
                        val newItem = InformationItem(
                            siteName = currentState.siteName,
                            userId = currentState.userId,
                            userPw = currentState.userPw,
                            memo = currentState.memo
                        )
                        addInformationItemUseCase(newItem)
                        _event.emit(InformationEvent.ShowSnackbar("새로운 정보가 추가되었습니다."))
                    }
                    
                    _state.update { 
                        it.copy(
                            isDialogVisible = false,
                            selectedItemForEdit = null
                        ) 
                    }
                    clearForm()
                }
            }
            is InformationAction.DeleteInformation -> {
                viewModelScope.launch {
                    deleteInformationItemUseCase(action.item)
                    _event.emit(InformationEvent.ShowSnackbar("정보가 삭제되었습니다."))
                }
            }
            is InformationAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(InformationEvent.NavigateBack)
                }
            }
        }
    }

    private fun clearForm() {
        _state.update { 
            it.copy(
                siteName = "",
                userId = "",
                userPw = "",
                memo = ""
            ) 
        }
    }
}

