package com.example.assignmnet_img.search.viewmdoel.repsitory

import com.example.assignmnet_img.search.dataclass.SearchModel

interface SearchRepository {

    suspend fun getSearchedImages(text: String): List<SearchModel>
    suspend fun getSearchVideos(text: String): List<SearchModel>

}

// 도메인 영역
// 단순한 인터페이스
// 구현체가 없음
// 리포지토리 패턴/브릿지 패턴