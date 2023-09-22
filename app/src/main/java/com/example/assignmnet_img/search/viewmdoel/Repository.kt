package com.example.assignmnet_img.search.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.ResultVideoModel
import com.example.assignmnet_img.search.dataclass.SearchModel
import com.example.assignmnet_img.search.retrofit.RetrofitClient

interface Repository {
    suspend fun getSearchedImages(text: String): List<SearchModel>
    suspend fun getSearchVideos(text: String): List<SearchModel>
}

class RepositoryImpl(
    private val client: RetrofitClient
) : Repository {

    override suspend fun getSearchedImages(text: String): List<SearchModel> {
        val responseImages = client.api.searchImage(text, "recency", 20)
        val responseList = responseImages.documents
        val resultList = responseList.map {document->
            SearchModel(
                Url = document.image_url,
                label = "[IMG]",
                title = document.display_sitename,
                datetime = document.datetime
            )
        }

        return resultList
    }

    override suspend fun getSearchVideos(text: String): List<SearchModel>{
        val responseVideos = client.api.searchVideo(text, "recency", 20)
        val responseList = responseVideos.documents
        val resultList = responseList.map { document ->
            SearchModel(
                Url = document.thumbnail,
                label = "[Video]",
                title = document.title,
                datetime = document.datetime,
            )
        }

        return resultList
    }

}