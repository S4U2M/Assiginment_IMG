package com.example.assignmnet_img.search.retrofit

import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.ResultVideoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort:String,
        @Query("size") size: Int
    ): ResultImgModel

    @GET("v2/search/vclip")
    suspend fun searchVideo(
        @Query("query") query: String?,
        @Query("sort") sort:String,
        @Query("size") size: Int
    ) : ResultVideoModel

}

