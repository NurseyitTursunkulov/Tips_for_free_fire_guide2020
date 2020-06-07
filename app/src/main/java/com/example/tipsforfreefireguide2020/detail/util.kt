package com.example.tipsforfreefireguide2020.detail

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.androidsx.rateme.RateMeDialog
import com.androidsx.rateme.RateMeDialogTimer
import com.example.tipsforfreefireguide2020.R
import com.example.tipsforfreefireguide2020.bookList.getAdRequestFire
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_screen_slidefire.*
import java.util.*


fun BookDetailFragmentFire.initPendingIndicatorViewFire() {
    val book = viewModelFire.navigateToDetailEvent.value?.peekContent()

    pageIndicatorView.count =
        book?.listOfContentPerPage?.size ?: 1 // specify total count of indicators

    pageIndicatorView.selection = 1
}

fun Fragment.showBannerAdvertFire(adView: AdView, showAdvertState: Boolean) {
    if (showAdvertState) {
        adView.visibility = View.VISIBLE
        val adRequest = getAdRequestFire()
        adView.loadAd(adRequest)
    }
}

fun Fragment.showRateMeDialogFire() {
    RateMeDialogTimer.onStart(requireContext())
    if (RateMeDialogTimer.shouldShowRateDialog(requireContext(), 1, 2)) {
        RateMeDialog.Builder(requireActivity().packageName, "")
            .setHeaderBackgroundColor(resources.getColor(R.color.colorPrimary))
            .setBodyBackgroundColor(resources.getColor(R.color.dialog_body))
            .showAppIcon(R.mipmap.ic_logo_foreground)
            .enableFeedbackByEmail("")
            .setRateButtonBackgroundColor(resources.getColor(R.color.dialog_button))
            .build()
            .show(requireActivity().fragmentManager, "plain-dialog")
    }
}

fun showInterstitialAdvertSafeFire(interstitialAd: InterstitialAd) {
    if (interstitialAd.isLoaded) {
        interstitialAd.show()
    } else {
        Log.d("Nurs", "first The interstitial wasn't loaded yet.")
    }
}

fun ScreenSlidePageFragmentFire.getRandomImageFire(): Int {
    val images =
        intArrayOf(
            R.drawable.foot1fire, //ok
            R.drawable.foot2fire, //ok
            R.drawable.foot3fire, //ok
            R.drawable.foot4fire, //ok
            R.drawable.foot5fire, //ok
            R.drawable.foot6fire, //ok
            R.drawable.foot7fire, //ok
            R.drawable.foot8fire, //ok
            R.drawable.foot9fire, //ok
            R.drawable.foot10fire, //ok
            R.drawable.foot11fire, //ok
            R.drawable.foot12fire, //ok
            R.drawable.foot13fire, //ok
            R.drawable.foot14fire, //ok
            R.drawable.foot15fire, //ok
            R.drawable.foot16fire //ok
        )
    val rand = Random()
    return images[rand.nextInt(images.size)]
}