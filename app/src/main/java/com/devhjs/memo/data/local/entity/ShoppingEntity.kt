package com.devhjs.memo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devhjs.memo.domain.model.ShoppingItem

@Entity(tableName = "shopping_items")
data class ShoppingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: String,
    val isChecked: Boolean = false
)

