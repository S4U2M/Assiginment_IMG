package com.example.assignmnet_img.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.assignmnet_img.R
import com.example.assignmnet_img.databinding.BookMarkFragmentBinding
import com.example.assignmnet_img.main.SharedViewModel
import com.example.assignmnet_img.search.dataclass.toBookmarkModel


class BookMarkFragment : Fragment() {

    companion object {

    }


    private var _binding: BookMarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val bookMarkAdapter by lazy {
        BookMarkAdapter()
    }

    private val bookmarkViewModel by lazy {
        ViewModelProvider(this)[BookMarkViewModel::class.java]
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookMarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        bookmarkRcList.adapter = bookMarkAdapter
    }

    private fun initViewModel() = with(bookmarkViewModel) {
        bookmarkList.observe(viewLifecycleOwner) {
            bookMarkAdapter.submitList(it)
        }

        sharedViewModel.liveSearchModel.observe(viewLifecycleOwner) {
            val updateBookmarkModel =
                sharedViewModel.liveSearchModel.value?.toBookmarkModel() ?: return@observe

            addModel(updateBookmarkModel)
        }
    }


}