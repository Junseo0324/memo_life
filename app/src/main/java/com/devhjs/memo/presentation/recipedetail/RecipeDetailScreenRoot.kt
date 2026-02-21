package com.devhjs.memo.presentation.recipedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecipeDetailScreenRoot(
    onBackClick: () -> Unit,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                RecipeDetailEvent.NavigateBack -> onBackClick()
            }
        }
    }

    RecipeDetailScreen(
        state = state,
        onAction = viewModel::onAction
    )
}