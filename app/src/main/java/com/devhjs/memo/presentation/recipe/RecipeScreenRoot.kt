package com.devhjs.memo.presentation.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecipeScreenRoot(
    onBackClick: () -> Unit,
    onNewRecipeClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                RecipeEvent.NavigateBack -> onBackClick()
                RecipeEvent.NavigateToWrite -> onNewRecipeClick()
                is RecipeEvent.NavigateToDetail -> onRecipeClick(event.id)
                is RecipeEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            RecipeScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }
    }
}