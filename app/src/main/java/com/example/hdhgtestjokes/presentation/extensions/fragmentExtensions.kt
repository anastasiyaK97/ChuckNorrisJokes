package com.example.hdhgtestjokes.presentation.extensions

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setActivityTitle(@StringRes id: Int) {
    (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(id)
}