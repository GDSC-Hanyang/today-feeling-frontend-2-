package com.naze.todayfeeling.util.extension

import android.content.Context
import android.widget.Toast

fun Context.makeToast(text:String) {
    Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
}