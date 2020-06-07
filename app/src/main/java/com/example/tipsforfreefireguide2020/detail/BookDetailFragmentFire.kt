package com.example.tipsforfreefireguide2020.detail

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tipsforfreefireguide2020.MainViewModelFire
import com.example.tipsforfreefireguide2020.R
import com.example.tipsforfreefireguide2020.util.removeFullScreenFire
import kotlinx.android.synthetic.main.activity_screen_slidefire.*
import kotlinx.android.synthetic.main.fragment_book_detail_fire.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BookDetailFragmentFire : Fragment(R.layout.activity_screen_slidefire) {

    val viewModelFire: MainViewModelFire by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreenFire()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.show()
        pager.adapter = ScreenSlidePagerAdapterFire(requireActivity())
        initPendingIndicatorViewFire()


        pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    pageIndicatorView.selection = position
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            if (pager.currentItem == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.
                this@BookDetailFragmentFire.findNavController().navigateUp()
            } else {
                // Otherwise, select the previous step.
                pager.currentItem = pager.currentItem - 1
                this@BookDetailFragmentFire.findNavController().navigateUp()
            }
        }

    }


    private inner class ScreenSlidePagerAdapterFire(fa: FragmentActivity) :
        FragmentStateAdapter(fa) {
        override fun getItemCount(): Int =
            viewModelFire.navigateToDetailEvent.value?.peekContent()?.listOfContentPerPage?.size
                ?: 1

        override fun createFragment(position: Int): Fragment {
            val content =
                viewModelFire.navigateToDetailEvent.value?.peekContent()?.listOfContentPerPage?.get(
                    position
                ) ?: ""
            return when (position) {
                0 -> ScreenSlideFirstPageFragmentFire.newInstance(content)
                else -> ScreenSlidePageFragmentFire.newInstance(
                    position,
                    content
                )
            }

        }
    }
}