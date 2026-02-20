package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import javax.inject.Inject

class AddInformationItemUseCase @Inject constructor(
    private val repository: InformationRepository
) {
    suspend operator fun invoke(item: InformationItem) {
        repository.insertInformationItem(item)
    }
}
