package com.example.converterv2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edit_text_current_amount: EditText
    private lateinit var textview_exhange_usd: TextView
    private lateinit var textview_result: TextView

    private fun count_up() {
        var getCurrentCurrency = intent.getStringExtra(SHARED_SAVE)
        if (!edit_text_current_amount.text.isEmpty()) {
            var getValueFragmentSetting = getCurrentCurrency.toString().toInt()
            var calculations =
                edit_text_current_amount.text.toString().toInt() * getValueFragmentSetting
            textview_result.text = "$calculations ${getString(R.string.tenge)}"
        } else {
            Toast.makeText(
                this,
                "${getString(R.string.toast_go_to_settings)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text_current_amount = findViewById(R.id.edit_text_current_amount)
        textview_exhange_usd = findViewById(R.id.textview_exhange_usd)
        textview_result = findViewById(R.id.textview_result)

        val image_button_setting: ImageButton = findViewById(R.id.image_button_setting)
        val button_result: Button = findViewById(R.id.button_result)

        image_button_setting.setOnClickListener(this)
        button_result.setOnClickListener(this)

        val getCurrentCurrency = intent.getStringExtra(SHARED_SAVE)

        if (getCurrentCurrency != null && edit_text_current_amount.text != null) {
            edit_text_current_amount.isEnabled = true
            textview_exhange_usd.text =
                "${getString(R.string.one_usd_qually)} " +
                        "$getCurrentCurrency ${getString(R.string.currency_usd)}"
        } else {
            edit_text_current_amount.isEnabled = false
            textview_result.text = "${getString(R.string.go_to_settings)}"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("KEY", textview_exhange_usd.text?.toString())
            putString("KEY2", textview_result.text?.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textview_exhange_usd.text = savedInstanceState.getString("KEY")
        textview_result.text = savedInstanceState.getString("KEY2")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_result -> count_up()
            R.id.image_button_setting -> {
                intent = Intent(this, SettingActivity::class.java);
                startActivity(intent)
            }
        }
    }
}