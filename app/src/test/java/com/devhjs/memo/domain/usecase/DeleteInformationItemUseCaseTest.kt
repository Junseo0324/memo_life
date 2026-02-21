package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteInformationItemUseCaseTest {

    private lateinit var deleteInformationItemUseCase: DeleteInformationItemUseCase
    private val repository: InformationRepository = mockk()

    @Before
    fun setUp() {
        deleteInformationItemUseCase = DeleteInformationItemUseCase(repository)
    }

    @Test
    fun `정보 아이템 삭제 시 리포지토리의 deleteInformationItem이 호출되어야 한다`() = runTest {
        // Given
        val item = InformationItem(id = 1, siteName = "삭제할 사이트", userId = "id", userPw = "pw", memo = "삭제")
        coEvery { repository.deleteInformationItem(any()) } returns Unit

        // When
        deleteInformationItemUseCase(item)

        // Then
        coVerify(exactly = 1) { repository.deleteInformationItem(item) }
    }
}
