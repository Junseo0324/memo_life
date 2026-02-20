package com.devhjs.memo.domain.model

data class InformationItem(
    val id: Int = 0,
    val siteName: String,
    val userId: String,
    val userPw: String,
    val memo: String
)
