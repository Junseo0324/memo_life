package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddInformationItemUseCaseTest {

    private lateinit var addInformationItemUseCase: AddInformationItemUseCase
    private val repository: InformationRepository = mockk()

    @Before
    fun setUp() {
        addInformationItemUseCase = AddInformationItemUseCase(repository)
    }

    @Test
    fun `정보 아이템 추가 시 리포지토리의 insertInformationItem이 호출되어야 한다`() = runTest {
        // Given
        val item = InformationItem(siteName = "카카오", userId = "kakao_user", userPw = "pw123", memo = "카카오 계정")
        coEvery { repository.insertInformationItem(any()) } returns Unit

        // When
        addInformationItemUseCase(item)

        // Then
        coVerify(exactly = 1) { repository.insertInformationItem(item) }
    }
}
