package com.example.assignmnet_img.search.dataclass

data class ResultVideoModel(
    val meta: Meta,
    val documents: List<Document>
){
    data class Document(

        val title:String,
        val url : String,
        val datetime : String,
        val play_time : Int,
        val thumbnail:String,
        val author: String

        )
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )

}


