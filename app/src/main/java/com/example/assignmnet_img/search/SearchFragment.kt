package com.example.assignmnet_img.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignmnet_img.BuildConfig
import com.example.assignmnet_img.databinding.SearchFragmentBinding
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


class SearchFragment : Fragment() {

    companion object{
        const val API_KEY = BuildConfig.API_KAKAO_SEARCH_KEY
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get()= _binding!!

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
        suspend fun searchImage(
            @Header("Authorization") apiKey: String = API_KEY,
            @Query("query") query: String,
            @Query("size") size: Int = 80,
            @Query("imgUrl") imgUrl : String,
            @Query("displaySiteName") displaySiteName : String,
            @Query("datetime") dateTime : String
        ):Call<KaKaoSearchManager>
    }

    class KaKaoSearchManager{



    }




}