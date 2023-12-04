package com.example.appbanhang

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.appbanhang.databinding.ActivityInsertProductBinding

class InsertProductActivity : AppCompatActivity() {
    private val binding by lazy { ActivityInsertProductBinding.inflate(layoutInflater) }

    companion object {
        const val RESULT = "RESULT"
        const val REQUEST_IMAGE_CAPTURE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {

        binding.nameProduct.setTextHint("Nhập tên sản phẩm")
        binding.priceProduct.setTextHintPrice("Nhập giá sản phẩm")
        binding.priceProduct.visibleEdtPrice()

        binding.getImage.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }

        /** User QR CODE */
        val result = intent?.getStringExtra(RESULT)

        if (result != null) {
            if (result.contains("https://") || result.contains("http://")) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(result))
                startActivity(intent)
            } else {
                Toast.makeText(this@InsertProductActivity, result.toString(), Toast.LENGTH_SHORT).show()
                binding.codeProduct.text = result.toString()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imgProduct.setImageBitmap(imageBitmap)
            binding.imgCover.visibility = View.GONE
        }
    }
}