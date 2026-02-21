package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteShoppingItemUseCaseTest {

    private lateinit var deleteShoppingItemUseCase: DeleteShoppingItemUseCase
    private val repository: ShoppingRepository = mockk()

    @Before
    fun setUp() {
        deleteShoppingItemUseCase = DeleteShoppingItemUseCase(repository)
    }

    @Test
    fun `쇼핑 아이템 삭제 시 리포지토리의 deleteShoppingItem이 호출되어야 한다`() = runTest {
        // Given
        val item = ShoppingItem(id = 1, name = "삭제할 우유", quantity = "1")
        coEvery { repository.deleteShoppingItem(any()) } returns Unit

        // When
        deleteShoppingItemUseCase(item)

        // Then
        coVerify(exactly = 1) { repository.deleteShoppingItem(item) }
    }
}
