package com.example.assignmnet_img.bookmark

import com.example.assignmnet_img.search.dataclass.SearchModel

data class BookmarkModel(
    val imageUrl: String,
    val label:String,
    val displaySiteName: String,
    val datetime: String,
    val isBookmark: Boolean = false
)

fun BookmarkModel.toSearchModel(): SearchModel {
    return SearchModel(
        Url = imageUrl,
        label = label,
        title = displaySiteName,
        datetime = datetime,
        isBookmark = isBookmark
    )
}