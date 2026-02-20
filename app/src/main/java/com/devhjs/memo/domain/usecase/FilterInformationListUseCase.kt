package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import javax.inject.Inject

class FilterInformationListUseCase @Inject constructor() {
    operator fun invoke(list: List<InformationItem>, query: String): List<InformationItem> {
        if (query.isBlank()) return list
        return list.filter {
            it.siteName.contains(query, ignoreCase = true) || 
            it.userId.contains(query, ignoreCase = true)
        }
    }
}
