package com.example.assignmnet_img.search.retrofit

import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.ResultVideoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("v2/search/image")
    fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort:String,
        @Query("size") size: Int
    ): Call<ResultImgModel>

    @GET("v2/search/vclip")
    fun searchVideo(
        @Query("query") query: String?,
        @Query("sort") sort:String,
        @Query("size") size: Int
    ) : Call<ResultVideoModel>

}

