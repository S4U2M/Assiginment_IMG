package com.example.assignmnet_img.search.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.SearchModel

class SearchViewModel() : ViewModel() {

    private val _searchList: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchList: LiveData<List<SearchModel>> get() = _searchList

    private fun findIndex(item: SearchModel?): Int {

        val currentList = searchList.value.orEmpty().toMutableList()
        val findItem = currentList.find {
            it.datetime == item?.datetime &&
                    it.imageUrl == item.imageUrl &&
                    it.displaySiteName == item.displaySiteName
        }

        return currentList.indexOf(findItem)

    }

    fun getList(list: List<SearchModel>) {
        var currentList = searchList.value.orEmpty().toMutableList()
        currentList.clear()
        currentList = list.toMutableList()
        _searchList.value = currentList
    }

    fun updateItem(item: SearchModel?) {
        if (item == null) return

        val currentList = searchList.value.orEmpty().toMutableList()
        val index = findIndex(item)

        currentList[index] = item

        _searchList.value = currentList
    }


}