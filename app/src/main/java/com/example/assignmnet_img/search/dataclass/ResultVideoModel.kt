package com.example.assignmnet_img.search.dataclass

data class ResultVideoModel(
    val meta: Meta,
    val documents: List<Document>
){
    data class Document(

        val collection: String,
        val image_url: String,
        val width: Int,
        val height: Int,
        val display_sitename: String,
        val doc_url: String,
        val datetime: String

        )
    data class Meta(
        val totalCount: Int,
        val pageableCount: Int,
        val is_end: Boolean
    )

}


