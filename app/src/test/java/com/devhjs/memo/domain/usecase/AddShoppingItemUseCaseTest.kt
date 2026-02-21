package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.ShoppingItem
import com.devhjs.memo.domain.repository.ShoppingRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddShoppingItemUseCaseTest {

    private lateinit var addShoppingItemUseCase: AddShoppingItemUseCase
    private val repository: ShoppingRepository = mockk()

    @Before
    fun setUp() {
        addShoppingItemUseCase = AddShoppingItemUseCase(repository)
    }

    @Test
    fun `쇼핑 아이템 추가 시 리포지토리의 insertShoppingItem이 호출되어야 한다`() = runTest {
        // Given
        val item = ShoppingItem(name = "우유", quantity = "2", isChecked = false)
        coEvery { repository.insertShoppingItem(any()) } returns Unit

        // When
        addShoppingItemUseCase(item)

        // Then
        coVerify(exactly = 1) { repository.insertShoppingItem(item) }
    }
}
