package com.example.postsapp.extenstions

import android.view.View
import androidx.constraintlayout.widget.Group


fun View.show() {
    if (visibility == View.VISIBLE) return

    visibility = View.VISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.hide() {
    if (visibility == View.GONE) return

    visibility = View.GONE
    if (this is Group) {
        this.requestLayout()
    }
}

fun View.invisible() {
    if (visibility == View.INVISIBLE) return

    visibility = View.INVISIBLE
    if (this is Group) {
        this.requestLayout()
    }
}

