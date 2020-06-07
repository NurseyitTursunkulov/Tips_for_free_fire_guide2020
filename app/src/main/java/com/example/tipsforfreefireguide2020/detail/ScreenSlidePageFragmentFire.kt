package com.example.tipsforfreefireguide2020.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tipsforfreefireguide2020.MainViewModelFire
import com.example.tipsforfreefireguide2020.R
import com.example.tipsforfreefireguide2020.util.EventObserver
import com.example.tipsforfreefireguide2020.util.removeFullScreenFire
import kotlinx.android.synthetic.main.detail_viewpager_fire.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ScreenSlidePageFragmentFire :
    Fragment(R.layout.detail_viewpager_fire) {
    private lateinit var content: String
    private var position: Int = 0

    val viewModelFire: MainViewModelFire by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            content = it.getString(CONTENT, "")
            position = it.getInt(POSITION)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreenFire()
        (activity as AppCompatActivity).supportActionBar?.show()
        content_text_view.text = content
        toolbar.title = viewModelFire.navigateToDetailEvent.value?.peekContent()?.title
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        viewModelFire.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialAdvertSafeFire(viewModelFire.interstitialAd)
        })
        showBannerAdvertFire(ad_view_detail_pager, viewModelFire.showAdvertState)

        Glide
            .with(this)
            .load(getRandomImageFire())
            .fitCenter()
            .into(imageView)

        showRateMeDialogFire()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Nurs", "onResume pos $position")
        if (position % 2 == 0) {
            Log.d("Nurs", "pos if $position")
            viewModelFire.showAdvertFire()
        }
    }

    companion object {
        const val POSITION = "position_"
        const val CONTENT = "content_"

        @JvmStatic
        fun newInstance(position: Int, content: String) =
            ScreenSlidePageFragmentFire().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                    putString(CONTENT, content)
                }
            }
    }
}