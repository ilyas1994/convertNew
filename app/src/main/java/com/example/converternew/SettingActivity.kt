package com.example.converterv2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val SHARED_PREF_KEY = "shared_pref_key"
const val SHARED_SAVE = "save_shared"

class SettingActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    private val settingRouter = SettingRouter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val editText_usd: EditText = findViewById(R.id.editText_usd)
        val button_save_setting_activity: Button = findViewById(R.id.button_save_setting_activity)

        val sharedPreferences_key = sharedPreferences.getString(SHARED_SAVE, "")
        editText_usd.setText(sharedPreferences_key)

        button_save_setting_activity.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(SHARED_SAVE, editText_usd.text.toString())
            editor.apply()
            val intent = settingRouter.createIntent(this, editText_usd.text.toString())
            startActivity(intent)
        }
    }
}