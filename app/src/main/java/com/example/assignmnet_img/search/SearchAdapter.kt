package com.example.assignmnet_img.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmnet_img.databinding.SearchItemBinding
import com.example.assignmnet_img.search.dataclass.ResultImgModel
import com.example.assignmnet_img.search.dataclass.SearchModel

class SearchAdapter : ListAdapter<SearchModel, SearchAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder(private val binding: SearchItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchModel) = with(binding) {
            searchItemDate.text = item.datetime
            searchItemTitle.text = item.displaySiteName
        }
    }
}


