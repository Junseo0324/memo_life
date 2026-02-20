package com.devhjs.memo.presentation.info

sealed interface InformationAction {
    data class UpdateSearchQuery(val query: String) : InformationAction
    object ShowDialog : InformationAction
    object HideDialog : InformationAction
    data class UpdateSiteName(val siteName: String) : InformationAction
    data class UpdateUserId(val userId: String) : InformationAction
    data class UpdateUserPw(val userPw: String) : InformationAction
    data class UpdateMemo(val memo: String) : InformationAction
    object AddInformation : InformationAction
    object ClearForm : InformationAction
    object OnBackClick : InformationAction
}
