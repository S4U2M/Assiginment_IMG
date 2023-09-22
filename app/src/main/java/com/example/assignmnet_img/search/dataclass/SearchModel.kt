package com.example.assignmnet_img.search.dataclass

import com.example.assignmnet_img.bookmark.BookmarkModel

data class SearchModel
    (
    val Url: String,
    val label:String,
    val title: String,
    val datetime: String,
    val isBookmark: Boolean = false
)

fun SearchModel.toBookmarkModel(): BookmarkModel {
    return BookmarkModel(
        imageUrl = Url,
        label = label,
        displaySiteName = title,
        datetime = datetime,
        isBookmark = isBookmark
    )
}