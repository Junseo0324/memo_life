package com.devhjs.memo.presentation.info

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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

    // TODO: InformationScreen 에 SnackbarHost 전달 필요 시 추가 구현

    InformationScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}