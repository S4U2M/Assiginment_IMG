package com.example.assignmnet_img.provider

import android.content.Context
import android.content.SharedPreferences

interface ContextProvider{
    fun getSharedPreferences(name:String): SharedPreferences

}

class ContextProviderImpl(
    private val context : Context
):ContextProvider{
    override fun getSharedPreferences(name:String): SharedPreferences {
        return context.getSharedPreferences(name,Context.MODE_PRIVATE)
    }



}