package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInformationListUseCase @Inject constructor(
    private val repository: InformationRepository
) {
    operator fun invoke(): Flow<List<InformationItem>> {
        return repository.getInformationList()
    }
}
