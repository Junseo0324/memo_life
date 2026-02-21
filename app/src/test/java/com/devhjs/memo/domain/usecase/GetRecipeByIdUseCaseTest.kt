package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.repository.RecipeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRecipeByIdUseCaseTest {

    private lateinit var getRecipeByIdUseCase: GetRecipeByIdUseCase
    private val repository: RecipeRepository = mockk()

    @Before
    fun setUp() {
        getRecipeByIdUseCase = GetRecipeByIdUseCase(repository)
    }

    @Test
    fun `특정 ID로 레시피 조회 시 해당 레시피가 반환되어야 한다`() = runTest {
        // Given
        val recipeId = 1
        val expectedRecipe = Recipe(id = recipeId, title = "테스트 레시피", ingredients = emptyList(), instructions = emptyList())
        coEvery { repository.getRecipeById(recipeId) } returns expectedRecipe

        // When
        val result = getRecipeByIdUseCase(recipeId)

        // Then
        assertThat(result).isEqualTo(expectedRecipe)
    }

    @Test
    fun `존재하지 않는 ID로 조회 시 null이 반환되어야 한다`() = runTest {
        // Given
        val recipeId = 999
        coEvery { repository.getRecipeById(recipeId) } returns null

        // When
        val result = getRecipeByIdUseCase(recipeId)

        // Then
        assertThat(result).isNull()
    }
}
