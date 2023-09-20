package com.example.assignmnet_img.search.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.SearchModel

class SearchViewModel() : ViewModel() {

    private val _searchList: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchList: LiveData<List<SearchModel>> get() = _searchList

    private fun findItem(item: SearchModel?): SearchModel? {
        val currentList = searchList.value.orEmpty()

        if (item != null) {
            return currentList.find {
                it.datetime == item.datetime &&
                        it.imageUrl == item.imageUrl &&
                        it.displaySiteName == item.displaySiteName
            }
        }
        return null
    }


    private fun findIndex(item: SearchModel?): Int {

        val currentList = searchList.value.orEmpty().toMutableList()

        val findItem = findItem(item)

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

    fun compareUpdateItem(item: SearchModel?) {
        if (item == null) return
        if (findItem(item) == null) return
        else updateItem(item)

    }


}