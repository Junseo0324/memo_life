package com.devhjs.memo.presentation.info

import com.devhjs.memo.domain.model.InformationItem

data class InformationState(
    val searchQuery: String = "",
    val isDialogVisible: Boolean = false,
    val siteName: String = "",
    val userId: String = "",
    val userPw: String = "",
    val memo: String = "",
    val selectedItemForEdit: InformationItem? = null,
    val infoList: List<InformationItem> = emptyList(),
    val filteredInfoList: List<InformationItem> = emptyList()
)
