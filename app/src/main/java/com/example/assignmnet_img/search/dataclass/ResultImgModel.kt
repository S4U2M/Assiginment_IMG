package com.example.assignmnet_img.search.dataclass


data class ResultImgModel(
    val meta: Meta,
    val documents: List<Document>
){
    data class Document(
        val collection: String,
        val thumbnail_url: String,
        val image_url: String,
        val width: Int,
        val height: Int,
        val display_sitename: String,
        val doc_url: String,
        val datetime: String
    )

    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )

}




