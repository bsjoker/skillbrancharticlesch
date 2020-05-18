package ru.skillbranch.skillarticles.ui.custom.behaviors

import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.marginRight
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.ui.custom.ArticleSubmenu
import ru.skillbranch.skillarticles.ui.custom.Bottombar
import kotlin.math.max
import kotlin.math.min

class SubmenuBehavior : CoordinatorLayout.Behavior<ArticleSubmenu>() {
    // Set view as dependent on bottombar
    override fun layoutDependsOn(parent: CoordinatorLayout, child: ArticleSubmenu, dependency: View): Boolean {
        return dependency is Bottombar
    }

    // Will be called if dependent view has been changed
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ArticleSubmenu, dependency: View): Boolean {
        return if (dependency is Bottombar && dependency.translationY >= 0) {
            animate(child, dependency)
            true
        } else false
    }

    private fun animate(child: ArticleSubmenu, dependency: Bottombar) {
        // Calculate the ratio between height and width
        val fraction = dependency.translationY / dependency.minHeight

        // Set translationX (horizontal location of this view relative to its left position)
        child.translationX = (child.width + child.marginRight) * fraction

        Log.d("My_SubmenuBehavior", "fraction: ${fraction}, translationX: ${child.translationX}")
    }
}