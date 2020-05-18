package ru.skillbranch.skillarticles.extensions

import java.util.*

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    val str = if (ignoreCase) this?.toLowerCase(Locale.getDefault()) else this

    return if (str.isNullOrEmpty() || substr.isEmpty()) {
        emptyList()
    } else {
        substr.toRegex().findAll(str).map { it.range.first }.toList()
    }
}