package com.example.common.ext

import android.text.Editable

fun String.toEditable(): Editable{
    return Editable.Factory.getInstance().newEditable(this)
}