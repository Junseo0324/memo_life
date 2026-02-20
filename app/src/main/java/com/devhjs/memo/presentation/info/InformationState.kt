package com.devhjs.memo.presentation.info

import androidx.compose.runtime.Immutable
import com.devhjs.memo.domain.model.InformationItem

@Immutable
data class InformationState(
    val searchQuery: String = "",
    val isDialogVisible: Boolean = false,
    val selectedItemForEdit: InformationItem? = null,
    val infoList: List<InformationItem> = emptyList(),
    val filteredInfoList: List<InformationItem> = emptyList()
)
