package com.devhjs.memo.presentation.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.devhjs.memo.presentation.component.AddInfoDialog
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InformationScreenRoot(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: InformationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is InformationEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
                is InformationEvent.NavigateBack -> {
                    onBackClick()
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        InformationScreen(
            modifier = Modifier,
            state = state,
            onAction = viewModel::onAction
        )
        
        if (state.isDialogVisible) {
            AddInfoDialog(
                siteName = state.siteName,
                userId = state.userId,
                userPw = state.userPw,
                memo = state.memo,
                onSiteNameChange = { viewModel.onAction(InformationAction.UpdateSiteName(it)) },
                onUserIdChange = { viewModel.onAction(InformationAction.UpdateUserId(it)) },
                onUserPwChange = { viewModel.onAction(InformationAction.UpdateUserPw(it)) },
                onMemoChange = { viewModel.onAction(InformationAction.UpdateMemo(it)) },
                onDismiss = { viewModel.onAction(InformationAction.HideDialog) },
                onSave = { viewModel.onAction(InformationAction.AddInformation) }
            )
        }
    }
}