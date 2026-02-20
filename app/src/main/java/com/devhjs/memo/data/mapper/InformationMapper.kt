package com.devhjs.memo.data.mapper

import com.devhjs.memo.data.local.entity.InformationEntity
import com.devhjs.memo.domain.model.InformationItem

fun InformationEntity.toDomain(): InformationItem {
    return InformationItem(
        id = id,
        siteName = siteName,
        userId = userId,
        userPw = userPw,
        memo = memo
    )
}

fun InformationItem.toEntity(): InformationEntity {
    return InformationEntity(
        id = id,
        siteName = siteName,
        userId = userId,
        userPw = userPw,
        memo = memo
    )
}
