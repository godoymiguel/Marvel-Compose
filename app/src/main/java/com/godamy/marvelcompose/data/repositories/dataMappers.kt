package com.godamy.marvelcompose.data.repositories


import com.godamy.marvelcompose.data.entities.Reference
import com.godamy.marvelcompose.data.entities.ReferenceList
import com.godamy.marvelcompose.data.network.entities.ApiReferenceList

fun ApiReferenceList.toDomainModel(type: ReferenceList.Type): ReferenceList {
    return ReferenceList(
        type = type,
        items = items
            ?.let { items.map { Reference(it.name) } }
            ?: emptyList()
    )
}
