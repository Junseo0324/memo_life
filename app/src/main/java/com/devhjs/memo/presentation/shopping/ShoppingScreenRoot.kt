package com.devhjs.memo.presentation.shopping

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devhjs.memo.presentation.component.AddItemDialog
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ShoppingScreenRoot(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: ShoppingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ShoppingEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
                is ShoppingEvent.NavigateBack -> {
                    onBackClick()
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        ShoppingScreen(
            state = state,
            onAction = viewModel::onAction
        )
        
        if (state.isDialogVisible) {
            AddItemDialog(
                onDismiss = { viewModel.onAction(ShoppingAction.HideDialog) },
                onSave = { name, quantity -> 
                    viewModel.onAction(ShoppingAction.SaveShoppingItem(name, quantity)) 
                }
            )
        }
    }
}
