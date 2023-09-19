package com.example.assignmnet_img.search.dataclass


data class ResultImgModel(
    val imageUrl: String,
    val datetime: String,
    val displaySiteName: String
)
data class SearchResultIMG(
    val result:List<ResultImgModel>
)
