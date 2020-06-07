package com.example.tipsforfreefireguide2020

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tipsforfreefireguide2020.bookList.Fire
import com.example.tipsforfreefireguide2020.util.Event
import com.example.tipsforfreefireguide2020.util.getStringFire
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModelFire(application: Application) : AndroidViewModel(application) {
    private val _splashState = MutableLiveData<Event<SplashStateFire>>()
    val splashStateFire: LiveData<Event<SplashStateFire>> = _splashState

    var adView: AdView? = null
    lateinit var interstitialAd: InterstitialAd

    private val _showAdvertEvent: MutableLiveData<Event<Boolean>> =
        MutableLiveData<Event<Boolean>>()
    val showAdvertEvent: LiveData<Event<Boolean>> = _showAdvertEvent

    var showAdvertState = false

    private val _navigateToDetailEvent = MutableLiveData<Event<Fire>>()
    val navigateToDetailEvent: LiveData<Event<Fire>> = _navigateToDetailEvent

    private val _items = MutableLiveData<List<Fire>>().apply {
        value = getBooksListFire()
    }

    val items: LiveData<List<Fire>> = _items

    init {
        viewModelScope.launch {
            delay(3000)
            _splashState.postValue(
                Event(
                    SplashStateFire.MainActivity()
                )
            )
            showAdvertFire()
        }
    }

    fun showAdvertFire() {
        if (showAdvertState)
            _showAdvertEvent.postValue(Event(showAdvertState))
    }

    fun openBookFire(fire: Fire) {
        _navigateToDetailEvent.postValue(
            Event(
                fire
            )
        )
    }

    private fun getBooksListFire(): List<Fire> {
        return listOf(
            Fire(
                title = getStringFire(R.string.book1title),
                body = getStringFire(R.string.book1body),
                imageId = R.drawable.foot1fire
            ),
            Fire(
                title = getStringFire(R.string.book_1_title),
                body = getStringFire(R.string.book_1_body),
                imageId = R.drawable.foot2fire
            ),
            Fire(
                title = getStringFire(R.string.book_2_title),
                body = getStringFire(R.string.book_2_body),
                imageId = R.drawable.foot3fire
            ),
            Fire(
                title = getStringFire(R.string.book_3_title),
                body = getStringFire(R.string.book_3_body),
                imageId = R.drawable.foot4fire
            ),
            Fire(
                title = getStringFire(R.string.book_4_title),
                body = getStringFire(R.string.book_4_body),
                imageId = R.drawable.foot4fire
            ),
            Fire(
                title = getStringFire(R.string.book_5_title),
                body = getStringFire(R.string.book_5_body),
                imageId = R.drawable.foot5fire
            ),
            Fire(
                title = getStringFire(R.string.book_6_title),
                body = getStringFire(R.string.book_6_body),
                imageId = R.drawable.foot6fire
            ),
            Fire(
                title = getStringFire(R.string.book_7_title),
                body = getStringFire(R.string.book_7_body),
                imageId = R.drawable.foot7fire
            ),
            Fire(
                title = getStringFire(R.string.book_8_title),
                body = getStringFire(R.string.book_8_body),
                imageId = R.drawable.foot8fire
            ),
            Fire(
                title = getStringFire(R.string.book_9_title),
                body = getStringFire(R.string.book_9_body),
                imageId = R.drawable.foot9fire
            ),
            Fire(
                title = getStringFire(R.string.book_10_title),
                body = getStringFire(R.string.book_10_body),
                imageId = R.drawable.foot10fire
            ),
            Fire(
                title = getStringFire(R.string.book_12_title),
                body = getStringFire(R.string.book_12_body),
                imageId = R.drawable.foot11fire
            ),
            Fire(
                title = getStringFire(R.string.book_13_title),
                body = getStringFire(R.string.book_13_body),
                imageId = R.drawable.foot12fire
            ),
            Fire(
                title = getStringFire(R.string.book_14_title),
                body = getStringFire(R.string.book_14_body),
                imageId = R.drawable.foot13fire
            ),
            Fire(
                title = getStringFire(R.string.book_15_title),
                body = getStringFire(R.string.book_15_body),
                imageId = R.drawable.foot14fire
            ),
            Fire(
                title = getStringFire(R.string.book_16_title),
                body = getStringFire(R.string.book_16_body),
                imageId = R.drawable.foot15fire
            ),
            Fire(
                title = getStringFire(R.string.book_17_title),
                body = getStringFire(R.string.book_17_body),
                imageId = R.drawable.foot16fire
            )
        )
    }
}

sealed class SplashStateFire {
    class MainActivity : SplashStateFire()
}
