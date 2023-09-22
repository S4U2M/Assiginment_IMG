package com.example.assignmnet_img.search.viewmdoel

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.assignmnet_img.bookmark.provider.SharedProvider
import com.example.assignmnet_img.bookmark.provider.SharedProviderImpl
import com.example.assignmnet_img.search.dataclass.SearchModel
import com.example.assignmnet_img.search.retrofit.RetrofitClient
import com.example.assignmnet_img.search.viewmdoel.repsitory.SearchRepository
import com.example.assignmnet_img.search.viewmdoel.repsitory.SearchRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(
    private val contextProvider: SharedProvider,
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchList: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchList: LiveData<List<SearchModel>> get() = _searchList

    val searchText: MutableLiveData<String> = MutableLiveData()

    private fun findItem(item: SearchModel?): SearchModel? {
        val currentList = searchList.value.orEmpty()

        if (item != null) {
            return currentList.find {
                it.datetime == item.datetime && //혹시 모를 고유 아이디 부여가 꼬일 시 방어 기제로 상세한 비교
                        it.Url == item.Url &&
                        it.title == item.title
            }
        }
        return null
    }

    private fun clearList() {
        val currentList = searchList.value.orEmpty().toMutableList()
        currentList.clear()
        _searchList.value = currentList
    }

    private fun findIndex(item: SearchModel?): Int {

        val currentList = searchList.value.orEmpty().toMutableList()

        val findItem = findItem(item)

        return currentList.indexOf(findItem)

    }

    private fun sortList(baseList:MutableList<SearchModel>):List<SearchModel>{
        val sortedList = baseList.sortedByDescending { it.datetime }
        return sortedList
    }

    suspend fun doSearch(keyword:String){

        clearList()

        viewModelScope.launch {

            val resultImagesList = repository.getSearchedImages(keyword)
            val resultVideosList = repository.getSearchVideos(keyword)

            val currentList = searchList.value.orEmpty().toMutableList().apply {
                addAll(resultImagesList)
                addAll(resultVideosList)
            }
            val sortedList = sortList(currentList)

            _searchList.value = sortedList
        }
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

    fun updateText(text: String) {
        searchText.value = text
    }

    fun saveSearchText(text: String) {
        val sharedPrf = contextProvider.getSharedPreferences("name_search_text")
        val edit = sharedPrf.edit().apply {
            putString("key_search_text", text)
            apply()
        }
    }

    fun loadSearchText(view: EditText) {
        val sharedPrf = contextProvider.getSharedPreferences("name_search_text")
        val text = sharedPrf.getString("key_search_text", "")
        view.setText(text)
    }

}

class SearchViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                SharedProviderImpl(context),
                SearchRepositoryImpl(RetrofitClient)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }


}