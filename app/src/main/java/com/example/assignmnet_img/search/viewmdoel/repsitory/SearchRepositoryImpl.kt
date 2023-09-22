package com.example.assignmnet_img.search.viewmdoel.repsitory

import com.example.assignmnet_img.search.dataclass.SearchModel
import com.example.assignmnet_img.search.retrofit.RetrofitClient

class SearchRepositoryImpl(
    private val client: RetrofitClient
) : SearchRepository {

    //데이터 영역

    override suspend fun getSearchedImages(text: String): List<SearchModel> {
        val responseImages = client.api.searchImage(text, "recency", 20)
        val responseList = responseImages.documents
        val resultList = responseList.map { document ->
            SearchModel(
                Url = document.image_url,
                label = "[IMG]",
                title = document.display_sitename,
                datetime = document.datetime
            )
        }

        return resultList
    }

    override suspend fun getSearchVideos(text: String): List<SearchModel> {
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