package com.example.assignmnet_img.bookmark.sharedhelper

import android.content.Context
import com.example.assignmnet_img.bookmark.BookmarkModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BookMarkSharedPrfHelper {

    private const val KEY_BOOKMARK_LIST = "key_bookmark_list"
    private const val NAME_BOOKMARK_PRF = "name_bookmark_prf"

    fun saveBookmarkData(context: Context,list: List<BookmarkModel>?) {
        if (list == null) return
        val sharedPrf =
            context.getSharedPreferences(NAME_BOOKMARK_PRF, Context.MODE_PRIVATE)
        val editor = sharedPrf.edit()
        val json = Gson().toJson(list)
        editor.putString(KEY_BOOKMARK_LIST, json)
        editor.apply()
    }

    fun loadBookmarkData(context: Context): List<BookmarkModel> {
        val sharedPrf =
            context.getSharedPreferences(NAME_BOOKMARK_PRF, Context.MODE_PRIVATE)
        val json = sharedPrf.getString(KEY_BOOKMARK_LIST, null)

        return Gson().fromJson(json, object : TypeToken<List<BookmarkModel>>() {}.type)
            ?: emptyList()
    }
}
