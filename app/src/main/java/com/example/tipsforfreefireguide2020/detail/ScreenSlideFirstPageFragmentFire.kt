package com.example.tipsforfreefireguide2020.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tipsforfreefireguide2020.MainViewModelFire
import com.example.tipsforfreefireguide2020.databinding.FragmentBookDetailFireBinding
import com.example.tipsforfreefireguide2020.util.EventObserver
import kotlinx.android.synthetic.main.fragment_book_detail_fire.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ScreenSlideFirstPageFragmentFire : Fragment() {
    lateinit var content: String
    lateinit var viewDataBinding: FragmentBookDetailFireBinding
    val viewModelFire: MainViewModelFire by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = it.getString(CONTENT, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFire.showAdvertFire()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        viewDataBinding = FragmentBookDetailFireBinding.inflate(inflater, container, false).apply {
            bookInfo = viewModelFire.navigateToDetailEvent.value?.peekContent()
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content_text_view.text = content
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        showBannerAdvertFire(ad_view_detail, viewModelFire.showAdvertState)

        viewModelFire.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialAdvertSafeFire(viewModelFire.interstitialAd)
        })
        viewModelFire.navigateToDetailEvent.value?.peekContent()?.imageId?.let {
            Glide
                .with(this)
                .load(it)
                .fitCenter()
                .into(image)
        }
    }

    companion object {
        const val CONTENT = "content_"

        @JvmStatic
        fun newInstance(content: String) =
            ScreenSlideFirstPageFragmentFire().apply {
                arguments = Bundle().apply {
                    putString(CONTENT, content)
                }
            }
    }
}