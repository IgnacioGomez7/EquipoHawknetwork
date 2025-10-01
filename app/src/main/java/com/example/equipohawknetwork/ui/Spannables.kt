package com.example.equipohawknetwork.ui

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import com.google.android.material.color.MaterialColors

fun TextView.highlightWord(word: String) {
    val src = text?.toString() ?: return
    val start = src.indexOf(word, ignoreCase = true)
    if (start < 0) return
    val end = start + word.length
    val span = SpannableString(src)
    val color = MaterialColors.getColor(this, com.google.android.material.R.attr.colorPrimary, 0)
    span.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    span.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    text = span
}
