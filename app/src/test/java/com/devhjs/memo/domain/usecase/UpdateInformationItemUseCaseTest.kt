package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateInformationItemUseCaseTest {

    private lateinit var updateInformationItemUseCase: UpdateInformationItemUseCase
    private val repository: InformationRepository = mockk()

    @Before
    fun setUp() {
        updateInformationItemUseCase = UpdateInformationItemUseCase(repository)
    }

    @Test
    fun `정보 아이템 수정 시 리포지토리의 updateInformationItem이 호출되어야 한다`() = runTest {
        // Given
        val item = InformationItem(id = 1, siteName = "수정된 사이트", userId = "new_id", userPw = "new_pw", memo = "수정")
        coEvery { repository.updateInformationItem(any()) } returns Unit

        // When
        updateInformationItemUseCase(item)

        // Then
        coVerify(exactly = 1) { repository.updateInformationItem(item) }
    }
}
