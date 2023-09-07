package com.andersenlab.poq.utils

import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    this.isVisible = true
}

fun View.hide() {
    this.isVisible = false
}

