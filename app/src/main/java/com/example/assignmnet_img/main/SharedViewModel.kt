package com.example.assignmnet_img.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmnet_img.search.dataclass.SearchModel

class SharedViewModel:ViewModel() {

    val liveSearchModel : MutableLiveData<SearchModel> = MutableLiveData()

}