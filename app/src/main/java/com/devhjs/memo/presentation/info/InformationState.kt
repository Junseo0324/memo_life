package com.devhjs.memo.presentation.info

data class InformationState(
    val searchQuery: String = "",
    val isDialogVisible: Boolean = false,
    val siteName: String = "",
    val userId: String = "",
    val userPw: String = "",
    val memo: String = "",
    val infoList: List<InformationItem> = listOf(
        InformationItem("1", "네이버", "junso0324", "password123"),
        InformationItem("2", "카카오", "kakao_user", "kakaoPw!@#")
    )
)
