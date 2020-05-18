package ru.skillbranch.skillarticles.extensions

import android.text.Layout

fun Layout.getLineHeight(line: Int): Int {
    return getLineTop(line.inc()) - getLineTop(line)
}

// Returns the top of Layout after removing the extra padding applied by the Layout
fun Layout.getLineTopWithoutPadding(line: Int): Int {
    var lineTop = getLineTop(line)
    if (line == 0) {
        lineTop -= topPadding
    }
    bottomPadding
    return lineTop
}

// Returns the bottom of Layout after removing the extra padding applied by the Layout
fun Layout.getLineBottomWithoutPadding(line: Int): Int {
    var lineBottom = getLineBottomWithoutSpacing(line)
    if (line == lineCount.dec()) {
        lineBottom -= bottomPadding
    }
    return lineBottom
}

// Returns the line bottom discarding the line spacing aded
fun Layout.getLineBottomWithoutSpacing(line: Int): Int {
    val lineBottom = getLineBottom(line)
    val isLastLine = line == lineCount.dec()
    val hasLineSpacing = spacingAdd != 0f

    return if (!hasLineSpacing || isLastLine) {
        lineBottom
    } else {
        lineBottom - spacingAdd.toInt()
    }
}