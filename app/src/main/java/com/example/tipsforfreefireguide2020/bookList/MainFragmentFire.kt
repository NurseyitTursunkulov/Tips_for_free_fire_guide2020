package com.example.tipsforfreefireguide2020.bookList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tipsforfreefireguide2020.MainViewModelFire
import com.example.tipsforfreefireguide2020.R
import com.example.tipsforfreefireguide2020.databinding.FragmentMainFireBinding
import com.example.tipsforfreefireguide2020.util.EventObserver
import com.example.tipsforfreefireguide2020.util.removeFullScreenFire
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragmentFire : Fragment() {

    lateinit var viewDataBinding: FragmentMainFireBinding
    lateinit var listAdapterFire: TasksAdapterFire
    val viewModelFire: MainViewModelFire by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removeFullScreenFire()
        (activity as AppCompatActivity).supportActionBar?.show()
        viewDataBinding = FragmentMainFireBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModelFire
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelFire.navigateToDetailEvent.observe(viewLifecycleOwner,
            EventObserver {
                findNavController().navigate(R.id.action_mainFragment_to_bookDetailFragment)
            })
        viewModelFire.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            if (viewModelFire.interstitialAd.isLoaded) {
                viewModelFire.interstitialAd.show()
            } else {
                Log.d("Nurs", "mainfrag The interstitial wasn't loaded yet.")
            }
        })
        initAdapterFire()
    }

    override fun onPause() {
        viewModelFire.adView?.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModelFire.adView?.resume()
    }

    override fun onDestroy() {
        viewModelFire.adView?.destroy()
        super.onDestroy()
    }
}