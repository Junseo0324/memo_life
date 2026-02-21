package com.devhjs.memo.domain.usecase

import app.cash.turbine.test
import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetShoppingListUseCaseTest {

    private lateinit var getShoppingListUseCase: GetShoppingListUseCase
    private val repository: ShoppingRepository = mockk()

    @Before
    fun setUp() {
        getShoppingListUseCase = GetShoppingListUseCase(repository)
    }

    @Test
    fun `쇼핑 리스트 조회 시 리포지토리의 데이터를 Flow로 반환해야 한다`() = runTest {
        // Given
        val items = listOf(
            ShoppingItem(id = 1, name = "딸기", quantity = "1"),
            ShoppingItem(id = 2, name = "사과", quantity = "5")
        )
        every { repository.getShoppingList() } returns flowOf(items)

        // When & Then
        getShoppingListUseCase().test {
            assertThat(awaitItem()).isEqualTo(items)
            awaitComplete()
        }
    }
}
