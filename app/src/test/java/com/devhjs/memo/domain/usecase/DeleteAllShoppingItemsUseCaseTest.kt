package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.repository.ShoppingRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteAllShoppingItemsUseCaseTest {

    private lateinit var deleteAllShoppingItemsUseCase: DeleteAllShoppingItemsUseCase
    private val repository: ShoppingRepository = mockk()

    @Before
    fun setUp() {
        deleteAllShoppingItemsUseCase = DeleteAllShoppingItemsUseCase(repository)
    }

    @Test
    fun `모든 쇼핑 아이템 삭제 시 리포지토리의 deleteAllShoppingItems가 호출되어야 한다`() = runTest {
        // Given
        coEvery { repository.deleteAllShoppingItems() } returns Unit

        // When
        deleteAllShoppingItemsUseCase()

        // Then
        coVerify(exactly = 1) { repository.deleteAllShoppingItems() }
    }
}
