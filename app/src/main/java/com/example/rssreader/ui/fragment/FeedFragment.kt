package com.example.rssreader.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.databinding.FragmentFeedBinding
import com.example.rssreader.model.api.repository.FeedRepository
import com.example.rssreader.ui.extention.observeOnce
import com.example.rssreader.ui.viewmodel.FeedViewModel
import com.example.rssreader.ui.viewmodel.factory.FeedViewModelFactory
import com.example.rssreader.ui.widget.adapter.FeedAdapter
import com.example.rssreader.ui.widget.adapter.FeedItemDecoration

class FeedFragment(private val category: String = "") : Fragment(R.layout.fragment_feed) {

    private val viewModel: FeedViewModel by viewModels {
        FeedViewModelFactory(FeedRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFeedBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = FeedAdapter()
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.fragmentFeedContents.layoutManager = layoutManager
        binding.fragmentFeedContents.adapter = adapter
        binding.fragmentFeedContents.addItemDecoration(FeedItemDecoration())
        viewModel.fetchFeed(category)

        viewModel.feedLiveData.observeOnce(viewLifecycleOwner, Observer {
            adapter.addRssItemList(it.rssItemList)
        })
    }
}