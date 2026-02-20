package com.devhjs.memo.presentation.info

import com.devhjs.memo.domain.model.InformationItem

sealed interface InformationAction {
    data class UpdateSearchQuery(val query: String) : InformationAction
    data object ShowDialog : InformationAction
    data class ShowEditDialog(val item: InformationItem) : InformationAction
    data object HideDialog : InformationAction
    data class UpdateSiteName(val siteName: String) : InformationAction
    data class UpdateUserId(val userId: String) : InformationAction
    data class UpdateUserPw(val userPw: String) : InformationAction
    data class UpdateMemo(val memo: String) : InformationAction
    data object AddInformation : InformationAction
    data class DeleteInformation(val item: InformationItem) : InformationAction
    data object ClearForm : InformationAction
    data object OnBackClick : InformationAction
}
