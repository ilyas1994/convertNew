package com.example.converterv2

import android.content.Context
import android.content.Intent

class SettingRouter {

    fun createIntent(context: Context, editText: String):
            Intent = Intent(context, MainActivity::class.java).apply {
        putExtra(SHARED_SAVE, editText)
    }
}


