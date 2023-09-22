package com.example.assignmnet_img.search.dataclass

import com.example.assignmnet_img.bookmark.BookmarkModel

data class SearchModel
    (
    val id: Long = -1,
    val Url: String,
    val label:String,
    val title: String,
    val datetime: String,
    val isBookmark: Boolean = false
)

fun SearchModel.toBookmarkModel(): BookmarkModel {
    return BookmarkModel(
        id = id,
        imageUrl = Url,
        label = label,
        displaySiteName = title,
        datetime = datetime,
        isBookmark = isBookmark
    )
}