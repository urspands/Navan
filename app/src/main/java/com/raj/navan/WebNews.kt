package com.raj.navan

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.raj.navan.databinding.WebLayoutBinding
import com.squareup.picasso.Picasso

class WebNews : AppCompatActivity() {
    private lateinit var _binding: WebLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = WebLayoutBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val url = intent.extras?.getString(URL, "")
        val content = intent.extras?.getString(CONTENT, "")
        bind(url = url!!, content!!)
//        binding.webView.loadUrl(url!!)
//        binding.share.setOnClickListener {
//            val sendIntent: Intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT, url!!)
//                type = "text/plain"
//            }
//
//            val shareIntent = Intent.createChooser(sendIntent, null)
//            startActivity(shareIntent)
//        }
    }

    private fun bind(url: String, content: String) {
        Log.d(TAG, "bind: content:: $content")
        Picasso.get().load(url).into(_binding.newsImage)
//        _binding.content.text = content
    }
    companion object{
        const val URL="url"
        const val CONTENT="content"
        const val TAG ="WebNews"
    }
}