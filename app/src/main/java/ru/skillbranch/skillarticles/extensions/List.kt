package ru.skillbranch.skillarticles.extensions

fun List<Pair<Int, Int>>.groupByBounds(bounds: List<Pair<Int, Int>>): List<MutableList<Pair<Int, Int>>> {
    val result: MutableList<MutableList<Pair<Int, Int>>> = mutableListOf()

    bounds.forEach { bound ->
        val subResult: MutableList<Pair<Int, Int>> = emptyList<Pair<Int, Int>>().toMutableList()
        val boundRange = bound.first..bound.second

        this.forEach {
            when {
                it.first in boundRange && it.second in boundRange ->
                    subResult.add(it)
                it.first in boundRange && it.second !in boundRange && it.first != boundRange.last ->
                    subResult.add(it.first to boundRange.last)
                it.first !in boundRange && it.second in boundRange && it.second != boundRange.first ->
                    subResult.add(boundRange.first to it.second)
            }
        }

        result.add(subResult)
    }

    return result
}