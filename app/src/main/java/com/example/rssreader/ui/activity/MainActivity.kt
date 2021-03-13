package com.example.rssreader.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.rssreader.R
import com.example.rssreader.databinding.ActivityMainBinding
import com.example.rssreader.model.api.repository.FeedCategoryListRepository
import com.example.rssreader.ui.extention.observeOnce
import com.example.rssreader.ui.viewmodel.MainViewModel
import com.example.rssreader.ui.viewmodel.factory.MainViewModelFactory
import com.example.rssreader.ui.widget.adapter.FeedPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        val viewPager = binding.activityMainViewpager

        val viewModel: MainViewModel by viewModels {
            MainViewModelFactory(FeedCategoryListRepository())
        }

        viewModel.categoryLiveData.observeOnce(this, Observer {
            if (it.categoryList.isNotEmpty()) {
                val pagerAdapter = FeedPagerAdapter(
                    this,
                    it.categoryList.map { category -> category.path }
                )
                viewPager.adapter = pagerAdapter
                TabLayoutMediator(binding.activityMainTabLayout, viewPager) { tab, position ->
                    tab.text = it.categoryList[position].localizedName
                }.attach()
            }
        })
        viewModel.fetchCategoryList()
    }
}