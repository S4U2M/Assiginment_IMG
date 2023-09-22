package com.example.assignmnet_img.search.viewmdoel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.ResultVideoModel
import com.example.assignmnet_img.search.dataclass.SearchModel
import com.example.assignmnet_img.search.retrofit.RetrofitClient
import com.example.assignmnet_img.search.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Response

interface Repository {
    suspend fun searchImage(text: String, sort: String): ResultImgModel
    suspend fun searchVideo(text: String, sort: String): ResultVideoModel
    fun responseImgToSearchList(response: ResultImgModel)
    fun responseVideoToSearchList(response: ResultVideoModel)

    fun getList(): MutableLiveData<List<SearchModel>>
}

class RepositoryImpl(
    private val client: RetrofitClient
) : Repository {

    private val _resultResponseList: MutableLiveData<List<SearchModel>> = MutableLiveData()
    private val resultResponseList: LiveData<List<SearchModel>> get() = _resultResponseList

    override suspend fun searchImage(text: String, sort: String): ResultImgModel {
        return client.api.searchImage(text, sort, 20)
    }

    override suspend fun searchVideo(text: String, sort: String): ResultVideoModel {
        return client.api.searchVideo(text, sort, 20)
    }

    override fun responseImgToSearchList(response: ResultImgModel) {
        val currentList = resultResponseList.value.orEmpty().toMutableList()
        val list = response.documents

        val responseList = list.map { document ->
            SearchModel(
                Url = document.image_url,
                label = "[IMG]",
                title = document.display_sitename,
                datetime = document.datetime
            )
        }

        currentList.addAll(responseList)
        _resultResponseList.value = currentList
    }

    override fun responseVideoToSearchList(response: ResultVideoModel) {
        val currentList = resultResponseList.value.orEmpty().toMutableList()
        val list = response.documents

        val responseList = list.map { document ->
            SearchModel(
                Url = document.thumbnail,
                label = "[Video]",
                title = document.title,
                datetime = document.datetime,
            )
        }

        currentList.addAll(responseList)
        _resultResponseList.value = currentList
    }

    override fun getList(): MutableLiveData<List<SearchModel>> {
        return _resultResponseList
    }


}