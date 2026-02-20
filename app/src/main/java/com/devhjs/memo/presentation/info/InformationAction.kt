package com.devhjs.memo.presentation.info

import com.devhjs.memo.domain.model.InformationItem

sealed interface InformationAction {
    data class UpdateSearchQuery(val query: String) : InformationAction
    data object ShowDialog : InformationAction
    data class ShowEditDialog(val item: InformationItem) : InformationAction
    data object HideDialog : InformationAction
    data class SaveInformation(val item: InformationItem) : InformationAction
    data class DeleteInformation(val item: InformationItem) : InformationAction
    data object OnBackClick : InformationAction
}
