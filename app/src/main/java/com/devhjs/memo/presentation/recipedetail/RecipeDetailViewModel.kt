package com.devhjs.memo.presentation.recipedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhjs.memo.domain.usecase.GetRecipeByIdUseCase
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
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeDetailState())
    val state: StateFlow<RecipeDetailState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<RecipeDetailEvent>()
    val event: SharedFlow<RecipeDetailEvent> = _event.asSharedFlow()

    init {
        val recipeId = savedStateHandle.get<String>("recipeId")?.toIntOrNull()
        recipeId?.let { loadRecipe(it) }
    }

    private fun loadRecipe(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val recipe = getRecipeByIdUseCase(id)
            _state.update { it.copy(
                recipe = recipe,
                isLoading = false
            ) }
        }
    }

    fun onAction(action: RecipeDetailAction) {
        when (action) {
            RecipeDetailAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(RecipeDetailEvent.NavigateBack)
                }
            }
        }
    }
}
