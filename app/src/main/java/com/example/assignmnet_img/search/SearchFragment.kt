package com.example.assignmnet_img.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignmnet_img.BuildConfig
import com.example.assignmnet_img.databinding.SearchFragmentBinding
import com.example.assignmnet_img.search.dataclass.SearchResultIMG
import com.example.assignmnet_img.search.viewmdoel.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


class SearchFragment : Fragment() {

    companion object {
        const val API_KEY = BuildConfig.API_KAKAO_SEARCH_KEY
        const val BASE_URL = "https://dapi.kakao.com/"
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val searchAdapter by lazy {
        SearchAdapter()
    }

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        searchList.adapter = searchAdapter

    }

    private fun initViewModel() = with(viewModel) {
        searchList.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }

    }

    interface ImgSearchApi {
        @GET("v2/search/image")
        fun searchImage(
            @Header("Authorization") apiKey: String = API_KEY,
            @Query("query") query: String
        ): Call<SearchResultIMG>
    }

    private fun searchIMG(keyword: String) {
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader("Authorization", API_KEY)
                .build()
            chain.proceed(request)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        val api = retrofit.create(ImgSearchApi::class.java)
        val call = api.searchImage(API_KEY,keyword)

       call.enqueue(object : Callback<SearchResultIMG>{
           override fun onResponse(call: Call<SearchResultIMG>, response: Response<SearchResultIMG>) {
               Log.d("Test", "Raw: ${response.raw()}")
               Log.d("Test", "Body: ${response.body()}")
           }

           override fun onFailure(call: Call<SearchResultIMG>, t: Throwable) {
               Log.d("Test","통신실패")
           }

       })
    }

}