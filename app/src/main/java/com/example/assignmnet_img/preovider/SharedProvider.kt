package com.example.assignmnet_img.bookmark.provider

import android.content.Context
import android.content.SharedPreferences

interface SharedProvider {
    fun getSharedPreferences(name: String): SharedPreferences


}

class SharedProviderImpl(
    private val context: Context
) : SharedProvider {

    override fun getSharedPreferences(name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }


}