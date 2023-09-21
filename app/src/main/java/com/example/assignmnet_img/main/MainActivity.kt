package com.example.assignmnet_img.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.compose.ui.text.font.Typeface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmnet_img.R
import com.example.assignmnet_img.bookmark.viewmodel.BookMarkViewModel
import com.example.assignmnet_img.databinding.MainActivityBinding
import com.example.assignmnet_img.search.viewmdoel.SearchViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.common.util.Utility


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initViewModel(){

    }

    private fun initView() = with(binding) {

        mainViewpager2.adapter = viewPagerAdapter
        mainViewpager2.offscreenPageLimit = 1// 탭 레이아웃에 연결된 index = 1번째까지 미리 만들어두겠다

        TabLayoutMediator(mainTab, mainViewpager2) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }


}