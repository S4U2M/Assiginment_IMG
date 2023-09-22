package com.example.assignmnet_img.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmnet_img.databinding.MainActivityBinding
import com.google.android.material.tabs.TabLayoutMediator


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

    private fun initViewModel() {

    }

    private fun initView() = with(binding) {

        mainViewpager2.adapter = viewPagerAdapter
        mainViewpager2.offscreenPageLimit = 1// 탭 레이아웃에 연결된 index = 1번째까지 미리 만들어두겠다

        TabLayoutMediator(mainTab, mainViewpager2) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }


}