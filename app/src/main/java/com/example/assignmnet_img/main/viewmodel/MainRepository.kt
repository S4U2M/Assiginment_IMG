package com.example.assignmnet_img.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignmnet_img.bookmark.BookmarkModel
import com.example.assignmnet_img.bookmark.viewmodel.BookMarkViewModel
import com.example.assignmnet_img.search.viewmdoel.SearchViewModel

interface MainRepository {

}

class MainRepositoryImpl(
    private val bookMarkViewModel: BookMarkViewModel,
    private val searchViewModel: SearchViewModel
) : MainRepository {



}