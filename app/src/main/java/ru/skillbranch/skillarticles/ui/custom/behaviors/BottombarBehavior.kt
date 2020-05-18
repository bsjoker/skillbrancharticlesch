package ru.skillbranch.skillarticles.ui.custom.behaviors

import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import ru.skillbranch.skillarticles.ui.custom.Bottombar
import kotlin.math.max
import kotlin.math.min

class BottombarBehavior : CoordinatorLayout.Behavior<Bottombar>() {
    // Instruct CoordinatorLayout that we care about vertical scroll events
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Bottombar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    // Get the scroll event before the nested scrolling child (target) receives it
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Bottombar,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (!child.isSearchMode) {
            // Clamp translationY (vertical location of this view relative to its top position)
            child.translationY = max(0f, min(child.height.toFloat(), child.translationY + dy))
            Log.d("My_BottombarBehavior", "translationY: ${child.translationY}")
        }

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}