package com.example.assignmnet_img.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmnet_img.bookmark.BookmarkModel
import com.example.assignmnet_img.search.dataclass.SearchModel
import com.example.assignmnet_img.search.dataclass.toBookmarkModel

class SharedViewModel : ViewModel() {

    val liveSearchModel: MutableLiveData<SearchModel> = MutableLiveData()
    val liveBookMarkModel: MutableLiveData<BookmarkModel> = MutableLiveData()

    fun updateSearchModel(item: SearchModel) {
        liveSearchModel.value = item
    }

    fun updateBookMarkModel(item: BookmarkModel) {
        liveBookMarkModel.value = item
    }

}
