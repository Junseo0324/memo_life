package com.devhjs.memo.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhjs.memo.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeState())
    val state: StateFlow<RecipeState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<RecipeEvent>()
    val event: SharedFlow<RecipeEvent> = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            getRecipesUseCase().collectLatest { recipes ->
                _state.update { it.copy(
                    recipes = recipes.map { recipe ->
                        RecipeItemUiModel(
                            id = recipe.id ?: 0,
                            title = recipe.title,
                            description = recipe.ingredients.joinToString(", "),
                            date = formatDate(recipe.createdAt),
                            imageUrl = recipe.imageUrl
                        )
                    }
                ) }
            }
        }
    }

    private fun formatDate(timestamp: Long): String {
        return SimpleDateFormat("yyyy년 M월 d일", Locale.KOREAN).format(Date(timestamp))
    }

    fun onAction(action: RecipeAction) {
        when (action) {
            is RecipeAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(RecipeEvent.NavigateBack)
                }
            }
            is RecipeAction.OnNewRecipeClick -> {
                viewModelScope.launch {
                    _event.emit(RecipeEvent.NavigateToWrite)
                }
            }
            is RecipeAction.OnRecipeClick -> {
                viewModelScope.launch {
                    _event.emit(RecipeEvent.NavigateToDetail(action.id))
                }
            }
        }
    }
}
