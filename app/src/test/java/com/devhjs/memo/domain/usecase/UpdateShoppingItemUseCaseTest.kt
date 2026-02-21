package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateShoppingItemUseCaseTest {

    private lateinit var updateShoppingItemUseCase: UpdateShoppingItemUseCase
    private val repository: ShoppingRepository = mockk()

    @Before
    fun setUp() {
        updateShoppingItemUseCase = UpdateShoppingItemUseCase(repository)
    }

    @Test
    fun `쇼핑 아이템 수정 시 리포지토리의 updateShoppingItem이 호출되어야 한다`() = runTest {
        // Given
        val item = ShoppingItem(id = 1, name = "수정된 우유", quantity = "3", isChecked = true)
        coEvery { repository.updateShoppingItem(any()) } returns Unit

        // When
        updateShoppingItemUseCase(item)

        // Then
        coVerify(exactly = 1) { repository.updateShoppingItem(item) }
    }
}
