package com.example.rssreader.ui.widget.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rssreader.model.api.entity.HatenaRssItem

import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.databinding.ViewFeedItemBinding

class FeedAdapter() : RecyclerView.Adapter<FeedAdapter.CustomItemViewHolder>() {

    private val rssItemList: MutableList<HatenaRssItem> = mutableListOf()

    override fun getItemCount(): Int {
        return rssItemList.size
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.setRssItem(rssItemList.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        return CustomItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_feed_item,
                parent,
                false
            )
        )
    }

    fun addRssItemList(itemList: List<HatenaRssItem>) {
        this.rssItemList.addAll(itemList)
        notifyDataSetChanged()
    }

    class CustomItemViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private var binding: ViewFeedItemBinding = ViewFeedItemBinding.bind(view)

        fun setRssItem(rssItem: HatenaRssItem) {
            binding.rssItem = rssItem
            binding.rssItemClickListener = View.OnClickListener {

            }
        }

    }




}