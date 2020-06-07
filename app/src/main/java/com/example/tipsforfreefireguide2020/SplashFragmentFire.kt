package com.example.tipsforfreefireguide2020

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tipsforfreefireguide2020.util.EventObserver
import com.example.tipsforfreefireguide2020.util.divideTextToPartsFire
import com.example.tipsforfreefireguide2020.util.initAdvertFire
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashFragmentFire : Fragment(R.layout.splash_fragment_fire) {

    private val viewModelFire: MainViewModelFire by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelFire.adView = initAdvertFire(requireContext())
        val content = viewModelFire.items.value
        content?.let { bookList ->
            divideTextToPartsFire(bookList)
        }

        viewModelFire.splashStateFire.observe(viewLifecycleOwner,
            EventObserver {
                when (it) {
                    is SplashStateFire.MainActivity -> {
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }
                }
            })
        viewModelFire.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            if (viewModelFire.interstitialAd.isLoaded) {
                viewModelFire.interstitialAd.show()
            } else {
                Log.d("Nurs", "splash The interstitial wasn't loaded yet.")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}