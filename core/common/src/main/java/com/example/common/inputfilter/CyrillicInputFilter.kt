package com.example.common.inputfilter

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class CyrillicInputFilter : InputFilter {

    private val cyrillicPattern = Pattern.compile("[а-яА-Я\\s\\p{Punct}]*")

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val matcher = cyrillicPattern.matcher(source)
        return if (!matcher.matches()) "" else null
    }
}