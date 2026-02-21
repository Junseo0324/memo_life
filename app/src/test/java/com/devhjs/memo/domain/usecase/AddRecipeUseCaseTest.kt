package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddRecipeUseCaseTest {

    private lateinit var addRecipeUseCase: AddRecipeUseCase
    private val repository: RecipeRepository = mockk()

    @Before
    fun setUp() {
        addRecipeUseCase = AddRecipeUseCase(repository)
    }

    @Test
    fun `레시피 추가 시 리포지토리의 insertRecipe가 호출되어야 한다`() = runTest {
        // Given
        val recipe = Recipe(
            title = "테스트 레시피",
            ingredients = listOf("재료1", "재료2"),
            instructions = listOf("설명1", "설명2"),
            imageUrl = "test_url"
        )
        coEvery { repository.insertRecipe(any()) } returns Unit

        // When
        addRecipeUseCase(recipe)

        // Then
        coVerify(exactly = 1) { repository.insertRecipe(recipe) }
    }
}
