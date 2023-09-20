package com.example.assignmnet_img.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookMarkViewModel : ViewModel() {

    private val _bookmarkList: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkModel>> get() = _bookmarkList

    fun addItem(item: BookmarkModel?) {
        val currentList = bookmarkList.value.orEmpty().toMutableList()
        if (item == null) return
        currentList.add(item)
        _bookmarkList.value = currentList

    }

    private fun findIndex(item: BookmarkModel?): Int {
        val currentList = bookmarkList.value.orEmpty().toMutableList()
        val findItem = currentList.find { it == item }
        return currentList.indexOf(findItem)
    }

    fun removeItem(item: BookmarkModel?) {
        if (item == null) return
        val currentList = bookmarkList.value.orEmpty().toMutableList()

        currentList.removeAt(findIndex(item))

        _bookmarkList.value = currentList

    }

    fun compareItem(item: BookmarkModel) {
        val currentList = bookmarkList.value.orEmpty().toMutableList()

        var compareItem = currentList.find {
            it.datetime == item?.datetime &&
                    it.imageUrl == item.imageUrl &&
                    it.displaySiteName == item.displaySiteName
        }

        if (compareItem != null) {
            if (item.isBookmark != compareItem.isBookmark) removeItem(compareItem)
        } else addItem(item)
    }
}