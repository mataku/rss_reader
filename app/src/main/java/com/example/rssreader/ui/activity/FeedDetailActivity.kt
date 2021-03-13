package com.example.rssreader.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rssreader.R
import com.example.rssreader.databinding.ActivityFeedDetailBinding

class FeedDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.extras?.getString(BUNDLE_KEY_URL)
        if (url == null || url.isEmpty()) {
            Toast.makeText(
                this,
                R.string.error_message_failed_to_show_feed_detail,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        val binding = DataBindingUtil.setContentView<ActivityFeedDetailBinding>(
            this,
            R.layout.activity_feed_detail
        )
        val toolbar = binding.activityFeedDetailToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val webView = binding.activityFeedDetailWebview
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    class IntentBuilder(context: Context, url: String?) {
        val intent = Intent(context, FeedDetailActivity::class.java)

        init {
            intent.putExtra(BUNDLE_KEY_URL, url)
        }

        fun build(): Intent {
            return intent
        }
    }

    companion object {
        private const val BUNDLE_KEY_URL = "url"
    }
}