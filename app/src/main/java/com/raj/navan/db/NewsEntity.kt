package com.raj.navan.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raj.navan.repo.NewsResponse

@Entity
data class NewsEntity(@PrimaryKey val search: String, val newsResponse: NewsResponse) {
}