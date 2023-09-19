package com.example.assignmnet_img.search.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmnet_img.search.dataclass.ResultImgModel

class SearchViewModel: ViewModel() {

    private val _searchList : MutableLiveData<List<ResultImgModel>> = MutableLiveData()
    val searchList : LiveData<List<ResultImgModel>> get() = _searchList


}