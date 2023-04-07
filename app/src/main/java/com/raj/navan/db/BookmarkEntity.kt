package com.raj.navan.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkEntity(@PrimaryKey val url: String, val isBookmarked: Boolean)