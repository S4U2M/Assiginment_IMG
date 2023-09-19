package com.example.assignmnet_img.search.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.SearchModel

class SearchViewModel: ViewModel() {

    private val _searchList : MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchList : LiveData<List<SearchModel>> get() = _searchList


}