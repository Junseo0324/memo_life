package com.devhjs.memo.data.mapper

import com.devhjs.memo.data.local.entity.ShoppingEntity
import com.devhjs.memo.domain.model.ShoppingItem

fun ShoppingEntity.toDomain(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
        isChecked = isChecked
    )
}

fun ShoppingItem.toEntity(): ShoppingEntity {
    return ShoppingEntity(
        id = id,
        name = name,
        quantity = quantity,
        isChecked = isChecked
    )
}
