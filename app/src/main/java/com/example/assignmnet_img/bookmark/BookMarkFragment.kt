package com.example.assignmnet_img.bookmark

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignmnet_img.bookmark.viewmodel.BookMarkViewModel
import com.example.assignmnet_img.bookmark.viewmodel.BookMarkViewModelFactory
import com.example.assignmnet_img.databinding.BookMarkFragmentBinding
import com.example.assignmnet_img.main.viewmodel.SharedViewModel
import com.example.assignmnet_img.search.dataclass.toBookmarkModel


class BookMarkFragment : Fragment() {

    private var _binding: BookMarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val bookMarkAdapter by lazy {
        BookMarkAdapter(
            onLongClickItem = { item ->
                alterDialog(item)
            }
        )
    }

    private val bookmarkViewModel by lazy {
        ViewModelProvider(
            this,
            BookMarkViewModelFactory(requireContext())
        )[BookMarkViewModel::class.java]
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
        bookmarkRcList.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initViewModel() {

        with(bookmarkViewModel) {
            bookmarkList.observe(viewLifecycleOwner) {
                bookMarkAdapter.submitList(it)
                saveBookmarkData(bookmarkList.value)
            }
        }

        with(sharedViewModel) {
            liveSearchModel.observe(viewLifecycleOwner) {
                val updateBookmarkModel =
                    liveSearchModel.value?.toBookmarkModel() ?: return@observe

                Log.d("북마크.도착", updateBookmarkModel.toString())
                Log.d("북마크.리스트", bookmarkViewModel.bookmarkList.value.toString())

                bookmarkViewModel.compareItem(updateBookmarkModel)
            }
        }
    }

    private fun alterDialog(item: BookmarkModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("삭제")
        builder.setMessage("북마크에서 삭제하시겠습니까?")
        builder.setNegativeButton("예") { _, _ ->
            val updateItem = item.copy(isBookmark = false)
            sharedViewModel.updateBookMarkModel(updateItem)

            bookmarkViewModel.removeItem(item)

            Toast.makeText(context, "북마크에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("아니오") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}