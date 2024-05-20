package com.develo.vectorket.profile.data.util

import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.reel.domain.model.Favorite

fun <T> List<T>.getItems(page: Int, pageSize: Int): List<T> {
    val startIndex = page * pageSize
    return if (startIndex + pageSize <= size) {
        slice(startIndex until startIndex + pageSize)
    } else if (page == 0 && size < pageSize) {
        this
    } else emptyList()
}

fun List<Vector>.sortWith(favorites: List<Favorite>): List<Vector> {
    val vectors = ArrayList<Vector>()

    favorites.forEach {
        find { vector ->
            vector.uid == it.vectorId
        }?.let { vectors.add(it) }
    }

    return vectors.toList()
}