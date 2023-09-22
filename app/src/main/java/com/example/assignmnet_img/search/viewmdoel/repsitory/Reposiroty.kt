package com.example.assignmnet_img.search.viewmdoel.repsitory

import com.example.assignmnet_img.search.dataclass.SearchModel

interface Repository {
    suspend fun getSearchedImages(text: String): List<SearchModel>
    suspend fun getSearchVideos(text: String): List<SearchModel>
}