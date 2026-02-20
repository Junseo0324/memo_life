package com.devhjs.memo.domain.repository

import com.devhjs.memo.domain.model.InformationItem
import kotlinx.coroutines.flow.Flow

interface InformationRepository {
    fun getInformationList(): Flow<List<InformationItem>>
    suspend fun insertInformationItem(item: InformationItem)
    suspend fun deleteInformationItem(item: InformationItem)
    suspend fun updateInformationItem(item: InformationItem)
    suspend fun deleteAllInformationItems()
}
