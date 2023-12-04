package com.example.appbanhang.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.widget.doOnTextChanged
import com.example.appbanhang.MoneyTextWatcher
import com.example.appbanhang.databinding.LayoutCustomEditTextBinding
import java.text.DecimalFormat

class CustomEditText : RelativeLayout {
    val binding by lazy { LayoutCustomEditTextBinding.inflate(LayoutInflater.from(context)) }

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        addView(binding.root)

        disableDelete()

        binding.edt.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotEmpty()) enableDelete() else disableDelete()
        }

        binding.edtPrice.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotEmpty()) enableDelete() else disableDelete()
        }

        binding.edtPrice.addTextChangedListener(MoneyTextWatcher(binding.edtPrice))

        binding.deleteText.setOnClickListener { clearText() }
    }

    private fun enableDelete() {
        binding.deleteText.visibility = View.VISIBLE
        binding.deleteText.isEnabled = true
    }

    private fun disableDelete() {
        binding.deleteText.visibility = View.GONE
        binding.deleteText.isEnabled = false
    }

    private fun clearText() {
        binding.edt.setText("")
        binding.edtPrice.setText("")
    }

    fun setTextHint(str: String){
        binding.edt.hint = str
    }

    fun setTextHintPrice(str: String){
        binding.edtPrice.hint = str
    }

    fun visibleEdtPrice(){
        binding.edt.visibility = View.GONE
        binding.edtPrice.visibility = View.VISIBLE
    }

    fun getText(): String = binding.edt.text.toString()
}