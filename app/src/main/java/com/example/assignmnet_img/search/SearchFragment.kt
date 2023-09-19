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
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.SearchModel
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
import java.util.concurrent.atomic.AtomicLong


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
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        searchRcList.adapter = searchAdapter

        searchBtnClick.setOnClickListener{

            searchIMG(searchEtText.text.toString())
            Log.d("테스트2", "initViewModel: ${viewModel.searchList.value.toString()}")
        }
    }

    private fun initViewModel() = with(viewModel) {
        searchList.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
            Log.d("테스트", "initViewModel: ${searchList.value.toString()}")
        }

    }

    private val setID = AtomicLong(1L)

    //retrofit interface
    interface ImgSearchApi {
        @GET("v2/search/image")
        fun searchImage(
            @Query("query") query: String
        ): Call<ResultImgModel>
    }


    // 검색을 위한 메소드
    private fun searchIMG(keyword: String) {
        //OkhttpClient
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK ${API_KEY}")
                .build()
            chain.proceed(request)
        }.build()

        //retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        val api = retrofit.create(ImgSearchApi::class.java)
        val call = api.searchImage(keyword)

       call.enqueue(object : Callback<ResultImgModel>{
           override fun onResponse(call: Call<ResultImgModel>, response: Response<ResultImgModel>) {
               val result = response.body()
               result?.documents?.let { documents ->

                   val resultList = documents.map { document ->
                       SearchModel(
                           id=setID.getAndIncrement(),
                           imageUrl = document.image_url,
                           displaySiteName = document.display_sitename,
                           datetime = document.datetime
                       )
                   }

                viewModel.getList(resultList)
               }
           }

           override fun onFailure(call: Call<ResultImgModel>, t: Throwable) {
               Log.d("Test","통신실패")
           }

       })
    }

}