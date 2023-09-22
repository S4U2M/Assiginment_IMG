package com.example.assignmnet_img.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmnet_img.databinding.SearchItemBinding
import com.example.assignmnet_img.search.dataclass.SearchModel

class SearchAdapter(

    private val onLongClickItem: (SearchModel) -> Unit

) : ListAdapter<SearchModel, SearchAdapter.ViewHolder>(
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
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onLongClickItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }


    class ViewHolder(
        private val binding: SearchItemBinding,
        private val onLongClickItem: (SearchModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context
        fun bind(item: SearchModel) = with(binding) {

            searchItemContainer.setOnLongClickListener {
                onLongClickItem(item)
                true
            }

            Glide.with(context)
                .load(item.Url.toUri())
                .into(searchItemImg)

            // glide 캐시타입에 대해 공부해 보기
            // 기본적으로 메모리와 디스크를 사용함

            // 코일에 대해서도 한 번 생각해보자 << 코루틴 때문

            // glide의 log레벨을 풀어서 봐보기
            // rmote -> 검색 disk -> remote보다는 조금 빠름. 과금은 되지 않음 << 하지만 내부 저장소는?
            // 캐시를 몇 분 단위로 삭제해야되는 로직이 필요하지 않을까? << 구글에서 검색기록 캐시 데이터를 생각해보자??
            // 앱의 캐시 데이터에 대해 생각해보자

            searchItemDate.text = item.datetime
            searchItemTitle.text = item.displaySiteName

            if (item.isBookmark) searchIvIsBookmark.visibility = View.VISIBLE
            else searchIvIsBookmark.visibility = View.INVISIBLE

        }
    }
}


