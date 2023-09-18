package com.example.assignmnet_img.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    private val _searchList : MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchList : LiveData<List<SearchModel>> get() = _searchList


}