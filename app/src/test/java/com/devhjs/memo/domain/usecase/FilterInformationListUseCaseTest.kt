package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.InformationItem
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FilterInformationListUseCaseTest {

    private lateinit var filterInformationListUseCase: FilterInformationListUseCase

    @Before
    fun setUp() {
        filterInformationListUseCase = FilterInformationListUseCase()
    }

    private val testList = listOf(
        InformationItem(siteName = "Naver", userId = "naver_user", userPw = "1", memo = ""),
        InformationItem(siteName = "Google", userId = "google_user", userPw = "2", memo = ""),
        InformationItem(siteName = "Kakao", userId = "kakao_id", userPw = "3", memo = "")
    )

    @Test
    fun `검색어가 비어있으면 전체 리스트를 반환해야 한다`() {
        val result = filterInformationListUseCase(testList, "")
        assertThat(result).isEqualTo(testList)
    }

    @Test
    fun `사이트 이름에 검색어가 포함된 항목만 필터링되어야 한다`() {
        val result = filterInformationListUseCase(testList, "nav")
        assertThat(result).hasSize(1)
        assertThat(result[0].siteName).isEqualTo("Naver")
    }

    @Test
    fun `사용자 ID에 검색어가 포함된 항목만 필터링되어야 한다`() {
        val result = filterInformationListUseCase(testList, "id")
        assertThat(result).hasSize(1)
        assertThat(result[0].userId).isEqualTo("kakao_id")
    }

    @Test
    fun `대소문자를 구분하지 않고 검색되어야 한다`() {
        val result = filterInformationListUseCase(testList, "GOOGLE")
        assertThat(result).hasSize(1)
        assertThat(result[0].siteName).isEqualTo("Google")
    }

    @Test
    fun `일치하는 항목이 없으면 빈 리스트를 반환해야 한다`() {
        val result = filterInformationListUseCase(testList, "None")
        assertThat(result).isEmpty()
    }
}
