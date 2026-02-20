package com.devhjs.memo.data.repository

import com.devhjs.memo.data.local.dao.InformationDao
import com.devhjs.memo.data.mapper.toDomain
import com.devhjs.memo.data.mapper.toEntity
import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InformationRepositoryImpl(
    private val dao: InformationDao
) : InformationRepository {

    override fun getInformationList(): Flow<List<InformationItem>> {
        return dao.getInformationList().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertInformationItem(item: InformationItem) {
        dao.insertInformationItem(item.toEntity())
    }

    override suspend fun deleteInformationItem(item: InformationItem) {
        dao.deleteInformationItem(item.toEntity())
    }

    override suspend fun updateInformationItem(item: InformationItem) {
        dao.updateInformationItem(item.toEntity())
    }

    override suspend fun deleteAllInformationItems() {
        dao.deleteAllInformationItems()
    }
}
