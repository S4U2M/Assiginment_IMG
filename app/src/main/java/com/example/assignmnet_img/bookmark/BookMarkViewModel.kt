package com.example.assignmnet_img.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookMarkViewModel : ViewModel() {

    private val _bookmarkList: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkModel>> get() = _bookmarkList

    fun addModel(item: BookmarkModel?) {
        if (item == null) return

        val currentList = bookmarkList.value.orEmpty().toMutableList().apply {
            add(item)
        }

        _bookmarkList.value = currentList

    }
}