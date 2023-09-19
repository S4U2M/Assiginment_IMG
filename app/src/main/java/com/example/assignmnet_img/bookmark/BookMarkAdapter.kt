package com.example.assignmnet_img.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmnet_img.databinding.BookMarkItemBinding
import com.example.assignmnet_img.databinding.SearchItemBinding
import com.example.assignmnet_img.search.dataclass.SearchModel

class BookMarkAdapter : ListAdapter<BookmarkModel, BookMarkAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<BookmarkModel>() {
        override fun areItemsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BookMarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: BookMarkItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context
        fun bind(item: BookmarkModel) = with(binding) {

            Glide.with(context)
                .load(item.imageUrl.toUri())
                .into(bookMarkItemImg)
            bookMarkItemDate.text = item.datetime
            bookMarkItemTitle.text = item.displaySiteName

        }
    }

}
