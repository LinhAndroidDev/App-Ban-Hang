package com.example.appbanhang

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat

class MoneyTextWatcher(private val editText: EditText) : TextWatcher {
    private val decimalFormat = DecimalFormat("#,###.##")
    private var previousValue = ""

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (s!!.isNotEmpty() && s.toString() != previousValue) {
            editText.removeTextChangedListener(this)

            val cleanString = s.toString().replace("\\D".toRegex(), "")
            val parsed = cleanString.toDouble()
            val formatted = decimalFormat.format(parsed)

            previousValue = formatted
            editText.setText(formatted)
            editText.setSelection(formatted.length)

            editText.addTextChangedListener(this)
        }
    }
}