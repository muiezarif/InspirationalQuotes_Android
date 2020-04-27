package com.muiezarif.inspirationalquotes

import android.app.Activity
import android.content.Context
import android.content.Intent

fun intentCallForwardRightToLeftAnim(c: Context, cls: Class<*>) {
    val intentInfo = Intent(c, cls)
    c.startActivity(intentInfo)
    (c as Activity).finish()
    c.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
}