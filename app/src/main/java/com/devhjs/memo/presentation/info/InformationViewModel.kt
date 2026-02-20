package com.devhjs.memo.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(InformationState())
    val state: StateFlow<InformationState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<InformationEvent>()
    val event: SharedFlow<InformationEvent> = _event.asSharedFlow()

    fun onAction(action: InformationAction) {
        when (action) {
            is InformationAction.UpdateSearchQuery -> {
                _state.update { it.copy(searchQuery = action.query) }
            }
            is InformationAction.ShowDialog -> {
                _state.update { it.copy(isDialogVisible = true) }
            }
            is InformationAction.HideDialog -> {
                _state.update { it.copy(isDialogVisible = false) }
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
                // TODO: 실제 Database 저장 로직 연동
                _state.update { it.copy(isDialogVisible = false) }
                clearForm()
                
                viewModelScope.launch {
                    _event.emit(InformationEvent.ShowSnackbar("새로운 정보가 추가되었습니다."))
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

