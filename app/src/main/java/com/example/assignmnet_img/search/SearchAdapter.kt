package com.example.assignmnet_img.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmnet_img.databinding.SearchItemBinding
import com.example.assignmnet_img.search.dataclass.ResultImgModel

class SearchAdapter : ListAdapter<ResultImgModel, SearchAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<ResultImgModel>() {
        override fun areItemsTheSame(oldItem: ResultImgModel, newItem: ResultImgModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultImgModel, newItem: ResultImgModel): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
    }

    class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item : ResultImgModel) = with(binding){

            }
    }
}