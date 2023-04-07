package com.raj.navan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raj.navan.databinding.ActivityMainBinding
import com.raj.navan.viewModel.MainViewModel
import com.raj.navan.viewModel.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val _viewModel: MainViewModel by viewModels()
    private val _textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            TODO("Not yet implemented")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            TODO("Not yet implemented")
        }

        override fun afterTextChanged(p0: Editable?) {
            _viewModel.doSearch(p0.toString())
        }
    }
    private lateinit var newsAdapter: NewsAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        newsAdapter = NewsAdapter(::onBookMarkClicked) {
            val intent = Intent(this@MainActivity, WebNews::class.java)
            intent.putExtra("url", it.web_url)
            startActivity(intent)
        }
        _binding.apply {
            etSearch.addTextChangedListener(_textWatcher)
            recyclerView.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(false)
            }
        }

        _viewModel.mediatorLiveData.observe(this) {
            when (it) {
                is UiState.Error -> Toast.makeText(this@MainActivity, "${it.e.toString()}", Toast.LENGTH_SHORT)
                    .show()
                UiState.Loading -> {
                    //loading progress
                }
                is UiState.NewsResponseSuccess -> {
                    Log.d("TAG", "onCreate: ${it.data.response.docs.size}")
                    newsAdapter.updateNews(it.data.response.docs)
                }
            }
        }
    }

    private fun onBookMarkClicked(url: String, isBookmarked: Boolean) {
        Log.d(TAG, "onBookMarkClicked: $isBookmarked")
        _viewModel.saveBookmark(url, isBookmarked)
    }

    companion object {
        const val TAG = "Main"
    }
}