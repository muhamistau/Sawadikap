package com.sawadikap.sawadikap.ui.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.ui.authentication.AuthActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    private val NUM_PAGES = 6
    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        setupViewPager()

        introButton.setOnClickListener {
            if (introViewPager.currentItem in 0..4) introViewPager.currentItem++
            else endTutorial()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViewPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        introViewPager.adapter = pagerAdapter
        introViewPager.currentItem = 0
        introViewPager.setSwipePagingEnabled(false)
        dotIndicator.setViewPager(introViewPager)
    }

    private fun endTutorial() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            var tp: IntroFragment? = null
            when (position) {
                0 -> tp = IntroFragment.newInstance(R.layout.fragment_intro_page_1, position)
                1 -> tp = IntroFragment.newInstance(R.layout.fragment_intro_page_2, position)
                2 -> tp = IntroFragment.newInstance(R.layout.fragment_intro_page_1, position)
                3 -> tp = IntroFragment.newInstance(R.layout.fragment_intro_page_1, position)
                4 -> tp = IntroFragment.newInstance(R.layout.fragment_intro_page_1, position)
                5 -> tp = IntroFragment.newInstance(R.layout.fragment_intro_page_1, position)
            }
            return tp!!
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }

}
