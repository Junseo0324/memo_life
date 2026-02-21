package com.devhjs.memo.domain.usecase

import app.cash.turbine.test
import com.devhjs.memo.domain.model.InformationItem
import com.devhjs.memo.domain.repository.InformationRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetInformationListUseCaseTest {

    private lateinit var getInformationListUseCase: GetInformationListUseCase
    private val repository: InformationRepository = mockk()

    @Before
    fun setUp() {
        getInformationListUseCase = GetInformationListUseCase(repository)
    }

    @Test
    fun `정보 리스트 조회 시 리포지토리의 데이터를 Flow로 반환해야 한다`() = runTest {
        // Given
        val items = listOf(
            InformationItem(id = 1, siteName = "네이버", userId = "user1", userPw = "pw1", memo = "메모1"),
            InformationItem(id = 2, siteName = "구글", userId = "user2", userPw = "pw2", memo = "메모2")
        )
        every { repository.getInformationList() } returns flowOf(items)

        // When & Then
        getInformationListUseCase().test {
            assertThat(awaitItem()).isEqualTo(items)
            awaitComplete()
        }
    }
}
