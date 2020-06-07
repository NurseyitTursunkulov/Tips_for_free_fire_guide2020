package com.example.tipsforfreefireguide2020.bookList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.*


fun MainFragmentFire.initAdapterFire() {
    val viewModel = viewDataBinding.viewmodel
    if (viewModel != null) {
        listAdapterFire = TasksAdapterFire(viewModel)
        viewDataBinding.recyclerViewBooks.adapter = listAdapterFire
        viewDataBinding.recyclerViewBooks.layoutManager = GridLayoutManager(requireContext(), 2)
        if (viewModel.showAdvertState)
            makeOneSpanForAdViewFire()
        viewModel.items.observe(viewLifecycleOwner, Observer {
            listAdapterFire.submitList(it)
        })
    } else {
        //            Timber.w("ViewModel not initialized when attempting to set up adapter.")
    }
}

class TaskDiffCallbackFire : DiffUtil.ItemCallback<Fire>() {

    override fun areItemsTheSame(oldItem: Fire, newItem: Fire): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Fire, newItem: Fire): Boolean {
        return oldItem == newItem
    }

}

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)

}

fun getAdRequestFire(): AdRequest? {
    val adRequest = AdRequest.Builder().build()
    val testDeviceIds = Arrays.asList("F5E4CD8EA025C4062D9E4BE54D002D25")
    val configuration =
        RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
    MobileAds.setRequestConfiguration(configuration)
    return adRequest
}

private fun MainFragmentFire.makeOneSpanForAdViewFire() {
    (viewDataBinding.recyclerViewBooks.layoutManager as GridLayoutManager)
        .spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (position) {
                0 -> 2
                else -> 1
            }
        }
    }
}
