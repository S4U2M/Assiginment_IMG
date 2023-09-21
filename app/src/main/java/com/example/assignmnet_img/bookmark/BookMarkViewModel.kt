package com.example.assignmnet_img.bookmark


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmnet_img.bookmark.provider.SharedProvider
import com.example.assignmnet_img.bookmark.provider.SharedProviderImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class BookMarkViewModel(
    private val contextProvider: SharedProvider
) : ViewModel() {

    private val _bookmarkList: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkModel>> get() = _bookmarkList

    init {
        _bookmarkList.value = mutableListOf<BookmarkModel>().apply {
            addAll(loadBookmarkData())
        }
    }

    private fun addItem(item: BookmarkModel?) {
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

    fun loadData(items: List<BookmarkModel>) {
        _bookmarkList.value = items
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

    fun saveBookmarkData(list: List<BookmarkModel>?) {
        if (list == null) return
        val sharedPrf = contextProvider.getSharedPreferences("name_bookmark")
        val editor = sharedPrf.edit()
        val json = Gson().toJson(list)
        editor.putString("key_bookmark", json)
        editor.apply()
    }

    private fun loadBookmarkData(): List<BookmarkModel> {
        val sharedPrf = contextProvider.getSharedPreferences("name_bookmark")
        val json = sharedPrf.getString("key_bookmark", null)

        return Gson().fromJson(json, object : TypeToken<List<BookmarkModel>>() {}.type)
            ?: emptyList()
    }

}

class BookMarkViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookMarkViewModel::class.java)) {
            return BookMarkViewModel(
                SharedProviderImpl(context)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}