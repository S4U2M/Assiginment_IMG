package com.example.assignmnet_img.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.compose.ui.text.font.Typeface
import com.example.assignmnet_img.R
import com.example.assignmnet_img.databinding.MainActivityBinding
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
    }

    private fun initView() = with(binding) {

        mainViewpager2.adapter = viewPagerAdapter

        TabLayoutMediator(mainTab,mainViewpager2){tab,position->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }


}