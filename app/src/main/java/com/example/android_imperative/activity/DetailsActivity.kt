package com.example.android_imperative.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android_imperative.adapter.TVShortAdapter
import com.example.android_imperative.databinding.ActivityDetailsBinding
import com.example.android_imperative.utils.Logger
import com.example.android_imperative.viewmodel.DetailsViewModel

class DetailsActivity : BaseActivity() {

    private val binding by lazy {ActivityDetailsBinding.inflate(layoutInflater)}
    private val TAG = DetailsActivity::class.java.simpleName
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        binding.rvShorts.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun initViews() {
        initObserves()

        val iv_detail: ImageView = binding.ivDetail
        binding.ivClose.setOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }

        val extras = intent.extras
        val show_id = extras!!.getLong("show_id")
        val show_img = extras.getString("show_img")
        val show_name = extras.getString("show_name")
        val show_network = extras.getString("show_network")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString("iv_movie")
            iv_detail.transitionName = imageTransitionName
        }

        binding.tvName.text = show_name
        binding.tvType.text = show_network
        Glide.with(this).load(show_img).into(iv_detail)

        viewModel.apiTVShowDetails(show_id.toInt())
    }

    private fun initObserves() {
        // Retrofit Related
        viewModel.tvShowDetails.observe(this,{
            Logger.d(TAG,it.toString())
            refreshAdapter(it.tvShow.pictures)
            binding.tvDetails.text = it.tvShow.description
            binding.pbLoading.isVisible = false
        })
        viewModel.isLoading.observe(this,{
            Logger.d(TAG,it.toString())
            if (viewModel.isLoading.value == true) {
                binding.pbLoading.visibility = View.VISIBLE
            }else {
                 binding.pbLoading.visibility = View.GONE
            }
        })
    }

    private fun refreshAdapter(items: List<String>) {
        val adapter = TVShortAdapter(this,items)
        binding.rvShorts.adapter = adapter
    }
}