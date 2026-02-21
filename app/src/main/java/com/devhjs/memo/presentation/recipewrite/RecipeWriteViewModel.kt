package com.devhjs.memo.presentation.recipewrite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhjs.memo.data.util.ImageStorage
import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.usecase.AddRecipeUseCase
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
class RecipeWriteViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val imageStorage: ImageStorage
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeWriteState())
    val state: StateFlow<RecipeWriteState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<RecipeWriteEvent>()
    val event: SharedFlow<RecipeWriteEvent> = _event.asSharedFlow()

    fun onAction(action: RecipeWriteAction) {
        when (action) {
            is RecipeWriteAction.UpdateTitle -> {
                _state.update { it.copy(title = action.title) }
            }
            is RecipeWriteAction.UpdateImage -> {
                _state.update { it.copy(imageUrl = action.uri) }
            }
            is RecipeWriteAction.AddIngredient -> {
                _state.update { it.copy(ingredients = it.ingredients + "") }
            }
            is RecipeWriteAction.UpdateIngredient -> {
                val newList = _state.value.ingredients.toMutableList()
                if (action.index in newList.indices) {
                    newList[action.index] = action.text
                    _state.update { it.copy(ingredients = newList) }
                }
            }
            is RecipeWriteAction.RemoveIngredient -> {
                val newList = _state.value.ingredients.toMutableList()
                if (action.index in newList.indices) {
                    newList.removeAt(action.index)
                    _state.update { it.copy(ingredients = newList) }
                }
            }
            is RecipeWriteAction.AddInstruction -> {
                _state.update { it.copy(instructions = it.instructions + "") }
            }
            is RecipeWriteAction.UpdateInstruction -> {
                val newList = _state.value.instructions.toMutableList()
                if (action.index in newList.indices) {
                    newList[action.index] = action.text
                    _state.update { it.copy(instructions = newList) }
                }
            }
            is RecipeWriteAction.RemoveInstruction -> {
                val newList = _state.value.instructions.toMutableList()
                if (action.index in newList.indices) {
                    newList.removeAt(action.index)
                    _state.update { it.copy(instructions = newList) }
                }
            }
            is RecipeWriteAction.SaveRecipe -> {
                saveRecipe()
            }
            is RecipeWriteAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(RecipeWriteEvent.NavigateBack)
                }
            }
        }
    }

    private fun saveRecipe() {
        val currentState = _state.value
        if (currentState.title.isBlank()) {
            viewModelScope.launch {
                _event.emit(RecipeWriteEvent.ShowSnackbar("제목을 입력해주세요."))
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            // 이미지를 앱 내부 저장소로 복사
            val localImagePath = imageStorage.saveImageToInternalStorage(currentState.imageUrl)
            
            addRecipeUseCase(
                Recipe(
                    title = currentState.title,
                    ingredients = currentState.ingredients.filter { it.isNotBlank() },
                    instructions = currentState.instructions.filter { it.isNotBlank() },
                    imageUrl = localImagePath ?: currentState.imageUrl
                )
            )
            _state.update { it.copy(isLoading = false) }
            _event.emit(RecipeWriteEvent.SaveSuccess)
        }
    }
}
