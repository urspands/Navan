package com.raj.navan

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.raj.navan.databinding.ItemLayoutBinding
import com.raj.navan.repo.Doc
import com.squareup.picasso.Picasso

class NewsAdapter(val onclick: (doc: Doc) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val item: ItemLayoutBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(doc: Doc, onclickNews: (doc: Doc) -> Unit) {
            item.apply {
                newsTitle.text = doc.headline.main
                doc.multimedia?.let {
                    if (it.isNotEmpty()) {
                        val image = "$IMAGE_BASE_URL${it[0].url}"
                        Log.d("TAG", "bind: $image")
//
                        Picasso.get().load(image).into(newsImage);
                    } else {
                        newsImage.setImageResource(android.R.color.transparent);
                        Log.e("TAG", "bind: the multimedia is empty")
                    }

                }
                bookmarkImage.setOnClickListener {
                    it.isSelected = !it.isSelected
                }

            }
            item.root.setOnClickListener {
                onclickNews(doc)
            }
        }

    }

    private val news = ArrayList<Doc>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], onclick)
    }


    fun updateNews(news1: List<Doc>) {
        news.clear()
        news.addAll(news1)
        notifyDataSetChanged()
    }

    companion object {
        const val IMAGE_BASE_URL = "https://static01.nyt.com/"
    }
}