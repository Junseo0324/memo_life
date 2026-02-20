package com.devhjs.memo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devhjs.memo.domain.model.InformationItem

@Entity(tableName = "information_items")
data class InformationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val siteName: String,
    val userId: String,
    val userPw: String,
    val memo: String
)

