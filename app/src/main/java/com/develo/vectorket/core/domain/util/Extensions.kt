package com.develo.vectorket.core.domain.util

fun String?.toList() = this?.split("|") ?: emptyList()

fun <T> List<T>.toSplittableString(): String {
    val sb = StringBuilder()

    forEachIndexed { index, value ->
        sb.append(value)
        if (index < size - 1) sb.append("|")
    }
    return sb.toString()
}

fun <T> List<T>.upsert(item: T) = this.toMutableList().also {
    it.remove(item)
    it.add(0, item)
}.toList()