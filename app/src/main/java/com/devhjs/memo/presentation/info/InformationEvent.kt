package com.devhjs.memo.presentation.info

sealed interface InformationEvent {
    data class ShowSnackbar(val message: String) : InformationEvent
    object NavigateBack : InformationEvent
}
