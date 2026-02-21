package com.devhjs.memo.domain.usecase

import app.cash.turbine.test
import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.repository.RecipeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRecipesUseCaseTest {

    private lateinit var getRecipesUseCase: GetRecipesUseCase
    private val repository: RecipeRepository = mockk()

    @Before
    fun setUp() {
        getRecipesUseCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `모든 레시피 조회 시 리포지토리에서 가져온 데이터가 Flow로 반환되어야 한다`() = runTest {
        // Given
        val recipes = listOf(
            Recipe(id = 1, title = "레시피1", ingredients = emptyList(), instructions = emptyList()),
            Recipe(id = 2, title = "레시피2", ingredients = emptyList(), instructions = emptyList())
        )
        every { repository.getAllRecipes() } returns flowOf(recipes)

        // When & Then
        getRecipesUseCase().test {
            val result = awaitItem()
            assertThat(result).isEqualTo(recipes)
            assertThat(result.size).isEqualTo(2)
            awaitComplete()
        }
    }
}
