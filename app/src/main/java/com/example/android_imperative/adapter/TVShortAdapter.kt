package com.example.android_imperative.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_imperative.R
import com.example.android_imperative.activity.DetailsActivity
import com.example.android_imperative.databinding.ItemTvShortBinding
import com.example.android_imperative.model.TVShow

class TVShortAdapter(var activity: DetailsActivity, var items: List<String>): BaseAdapter() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_short,parent,false)
        return TVShortViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvShort = items[position]
        if (holder is TVShortViewHolder) {
            Glide.with(activity).load(tvShort).into(holder.binding.ivShort)
        }
    }

    inner class TVShortViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemTvShortBinding.bind(view)
    }
}