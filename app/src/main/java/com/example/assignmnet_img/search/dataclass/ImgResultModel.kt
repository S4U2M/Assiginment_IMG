package com.example.assignmnet_img.search.dataclass


data class ImgResultModel(
    val imageUrl: String,
    val datetime: String,
    val displaySiteName: String
)
data class SearchResult(
    val result:List<ImgResultModel>
)
