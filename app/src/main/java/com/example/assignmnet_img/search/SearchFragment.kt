package com.example.assignmnet_img.search

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignmnet_img.BuildConfig
import com.example.assignmnet_img.databinding.SearchFragmentBinding
import com.example.assignmnet_img.main.viewmodel.SharedViewModel
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
import java.util.concurrent.atomic.AtomicLong
import androidx.fragment.app.activityViewModels
import com.example.assignmnet_img.bookmark.toSearchModel
import com.example.assignmnet_img.search.dataclass.ResultVideoModel
import com.example.assignmnet_img.search.retrofit.SearchApi
import com.example.assignmnet_img.search.viewmdoel.SearchViewModelFactory

class SearchFragment : Fragment() {

    companion object {
        const val API_KEY = BuildConfig.API_KAKAO_SEARCH_KEY
        const val BASE_URL = "https://dapi.kakao.com/"
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val searchAdapter by lazy {
        SearchAdapter(
            onLongClickItem = { item ->
                if (!item.isBookmark) {
                    addAlterDialog(item)
                    Log.d("북마크", sharedViewModel.liveSearchModel.value.toString())
                } else {
                    removeAlterDialog(item)
                }
            }
        )
    }

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(
            this,
            SearchViewModelFactory(requireContext())
        )[SearchViewModel::class.java]
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
        searchRcList.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.loadSearchText(searchEtText)

        searchEtText.setOnKeyListener { _, keycode, event ->
            val event = event.action == KeyEvent.ACTION_DOWN

            if (event && keycode == KeyEvent.KEYCODE_ENTER) {
                searchBtnClick.performClick()
                return@setOnKeyListener true
            }

            Log.d("키보드", keycode.toString())

            false
        }

        searchBtnClick.setOnClickListener {
            val text = searchEtText.text.toString()
            viewModel.doSearch(text)
            viewModel.updateText(text)
            Log.d("테스트2", "initViewModel: ${viewModel.searchList.value.toString()}")
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            searchList.observe(viewLifecycleOwner) {
                searchAdapter.submitList(it)
                Log.d("테스트", "initViewModel: ${searchList.value.toString()}")
            }
            searchText.observe(viewLifecycleOwner) {
                saveSearchText(searchText.value.toString())
            }
        }
        with(sharedViewModel) {
            liveBookMarkModel.observe(viewLifecycleOwner) {
                val updateItem = liveBookMarkModel.value?.toSearchModel()

                viewModel.compareUpdateItem(updateItem)
            }

        }


    }

    //sharedViewModel을 업데이트해주는 메소드
    private fun updateItem(item: SearchModel) = with(sharedViewModel) {

        sharedViewModel.updateSearchModel(item)

    }

    //id부여를 위한 변수




    private fun addAlterDialog(item: SearchModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("북마크 추가")
        builder.setMessage("북마크에 추가하시겠습니까?")
        builder.setNegativeButton("예") { _, _ ->

            val updateItem = item.copy(isBookmark = true)
            updateItem(updateItem)
            viewModel.updateItem(updateItem)

            Toast.makeText(context, "북마크에 저장되었습니다.", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("아니오") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun removeAlterDialog(item: SearchModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("북마크 삭제")
        builder.setMessage("북마크에서 삭제하시겠습니까?")
        builder.setNegativeButton("예") { _, _ ->

            val updateItem = item.copy(isBookmark = false)
            updateItem(updateItem)
            viewModel.updateItem(updateItem)

            Toast.makeText(context, "북마크에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("아니오") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }


}