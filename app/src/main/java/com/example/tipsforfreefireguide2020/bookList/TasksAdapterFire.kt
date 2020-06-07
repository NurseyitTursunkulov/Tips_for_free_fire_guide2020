/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tipsforfreefireguide2020.bookList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.tipsforfreefireguide2020.MainViewModelFire
import com.example.tipsforfreefireguide2020.R
import com.example.tipsforfreefireguide2020.databinding.ListItemBookFireBinding
import com.google.android.gms.ads.AdView

class TasksAdapterFire(private val viewModelFire: MainViewModelFire) :
    ListAdapter<Fire, RecyclerView.ViewHolder>(TaskDiffCallbackFire()) {
    private val MENU_ITEM_VIEW_TYPE = 0

    // The banner ad view type.
    private val BANNER_AD_VIEW_TYPE = 1
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ViewHolderFire -> holder.bindFire(viewModelFire, item)
            else -> {
                val bannerHolder = holder as AdViewHolderFire
                bannerHolder.bind()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MENU_ITEM_VIEW_TYPE -> ViewHolderFire.from(parent)
            else -> {
                val bannerLayoutView = LayoutInflater.from(
                    parent.context
                ).inflate(
                    R.layout.banner_ad_container_fire,
                    parent, false
                )
                AdViewHolderFire(bannerLayoutView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && viewModelFire.showAdvertState)
            BANNER_AD_VIEW_TYPE else MENU_ITEM_VIEW_TYPE
    }

    class ViewHolderFire private constructor(val binding: ListItemBookFireBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindFire(viewModelFire: MainViewModelFire, item: Fire) {

            binding.viewmodel = viewModelFire
            binding.task = item
            binding.imageViewBookCover.load(item.imageId) {
                crossfade(true)
                placeholder(R.drawable.bookdash_placeholderfire)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolderFire {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBookFireBinding.inflate(layoutInflater, parent, false)

                return ViewHolderFire(binding)
            }
        }
    }

    class AdViewHolderFire internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        var ad_view: AdView = view.findViewById(R.id.ad_viewR)
        fun bind() {
            val adRequest = getAdRequestFire()
            ad_view.loadAd(adRequest)
        }
    }
}
