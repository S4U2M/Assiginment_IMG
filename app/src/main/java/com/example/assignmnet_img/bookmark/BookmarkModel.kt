package com.example.assignmnet_img.bookmark

data class BookmarkModel(
    val id : Long = -1,
    val imageUrl: String,
    val displaySiteName: String,
    val datetime: String,
    val isBookmark : Boolean = false
)
