package com.example.assignmnet_img.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignmnet_img.R
import com.example.assignmnet_img.databinding.BookMarkFragmentBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BookMarkFragment : Fragment() {

    private var _binding:BookMarkFragmentBinding? = null
    private val binding get() =  _binding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_mark_fragment, container, false)
    }

    companion object {

    }
}