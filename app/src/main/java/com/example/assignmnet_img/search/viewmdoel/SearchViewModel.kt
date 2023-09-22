package com.example.assignmnet_img.search.viewmdoel

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmnet_img.bookmark.provider.SharedProvider
import com.example.assignmnet_img.bookmark.provider.SharedProviderImpl
import com.example.assignmnet_img.search.SearchFragment
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.ResultVideoModel
import com.example.assignmnet_img.search.dataclass.SearchModel
import com.example.assignmnet_img.search.retrofit.SearchApi
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel(
    private val contextProvider: SharedProvider
) : ViewModel() {

    private val _searchList: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchList: LiveData<List<SearchModel>> get() = _searchList

    val searchText: MutableLiveData<String> = MutableLiveData()

    private fun findItem(item: SearchModel?): SearchModel? {
        val currentList = searchList.value.orEmpty()

        if (item != null) {
            return currentList.find {
                it.id == item.id && //고유 아이디 비교
                        it.datetime == item.datetime && //혹시 모를 고유 아이디 부여가 꼬일 시 방어 기제로 상세한 비교
                        it.Url == item.Url &&
                        it.title == item.title
            }
        }
        return null
    }

fun clearList(){
    val currentList = searchList.value.orEmpty().toMutableList()
    currentList.clear()
    _searchList.value = currentList
}

    private fun findIndex(item: SearchModel?): Int {

        val currentList = searchList.value.orEmpty().toMutableList()

        val findItem = findItem(item)

        return currentList.indexOf(findItem)

    }

    fun getList(list: List<SearchModel>) {
        var currentList = searchList.value.orEmpty().toMutableList()
//        currentList.clear()
        currentList = list.toMutableList()
        currentList.addAll(list)
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

    fun doSearch(keyword: String){
        clearList()
        searchVideo(keyword)
        searchIMG(keyword)
    }

    private val setID = AtomicLong(1L)

    // 검색을 위한 메소드
    private fun searchIMG(keyword: String) {
        //OkhttpClient
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK ${SearchFragment.API_KEY}")
                .build()
            chain.proceed(request)
        }.build()

        //retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(SearchFragment.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        val api = retrofit.create(SearchApi::class.java)
        val call = api.searchImage(keyword,"recency",40)

        call.enqueue(object : Callback<ResultImgModel> {
            override fun onResponse(
                call: Call<ResultImgModel>,
                response: Response<ResultImgModel>
            ) {
                val result = response.body()
                result?.documents?.let { documents ->

                    val resultList = documents.map { document ->
                        SearchModel(
                            id = setID.getAndIncrement(),
                            Url = document.image_url,
                            label = "[IMG]",
                            title = document.display_sitename,
                            datetime = document.datetime
                        )
                    }
                    getList(resultList)
                }
            }
            override fun onFailure(call: Call<ResultImgModel>, t: Throwable) {
                Log.d("Test", "통신실패")
            }

        })
    }


    //비디오 검색
    private fun searchVideo(keyword: String) {
        //OkhttpClient
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK ${SearchFragment.API_KEY}")
                .build()
            chain.proceed(request)
        }.build()

        //retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(SearchFragment.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        val api = retrofit.create(SearchApi::class.java)
        val call = api.searchVideo(keyword, "recency",40)

        call.enqueue(object : Callback<ResultVideoModel> {
            override fun onResponse(
                call: Call<ResultVideoModel>,
                response: Response<ResultVideoModel>
            ) {
                val result = response.body()
                result?.documents?.let { documents ->
                    val resultList = documents.map { document ->
                        SearchModel(
                            id = setID.getAndIncrement(),
                            Url = document.thumbnail,
                            label = "[Video]",
                            title = document.title,
                            datetime = document.datetime,
                        )
                    }
                    getList(resultList)
                }
//                Log.d("비디오", response.body().toString())
            }
            override fun onFailure(call: Call<ResultVideoModel>, t: Throwable) {
                Log.d("Test", "통신실패")
            }

        })
    }

}

class SearchViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                SharedProviderImpl(context)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }


}