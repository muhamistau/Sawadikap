package com.sawadikap.sawadikap.ui.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntro2Fragment
import com.github.paolorotolo.appintro.model.SliderPagerBuilder
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.ui.main.MainActivity

class IntroActivity : AppIntro2() {

    private val PREF_NAME = "first_time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_intro)

        if (getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean(PREF_NAME, false))
            startActivity(Intent(this, MainActivity::class.java))

        val pageOne = SliderPagerBuilder()
            .title(getString(R.string.intro_title_1))
            .description(getString(R.string.intro_desc_1))
            .imageDrawable(R.drawable.ic_intro_begin_chat)
            .bgColor(resources.getColor(R.color.white))
            .build()

        val pageTwo = SliderPagerBuilder()
            .title(getString(R.string.intro_title_2))
            .description(getString(R.string.intro_desc_2))
            .imageDrawable(R.drawable.ic_intro_celebration)
            .bgColor(resources.getColor(R.color.white))
            .build()

        val pageThree = SliderPagerBuilder()
            .title(getString(R.string.intro_title_3))
            .description(getString(R.string.intro_desc_3))
            .imageDrawable(R.drawable.ic_intro_online_shopping)
            .bgColor(resources.getColor(R.color.white))
            .build()

        val pageFour = SliderPagerBuilder()
            .title(getString(R.string.intro_title_4))
            .description(getString(R.string.intro_desc_4))
            .imageDrawable(R.drawable.ic_intro_super_thank_you)
            .bgColor(resources.getColor(R.color.white))
            .build()

        addSlide(AppIntro2Fragment.newInstance(pageOne))
        addSlide(AppIntro2Fragment.newInstance(pageTwo))
        addSlide(AppIntro2Fragment.newInstance(pageThree))
        addSlide(AppIntro2Fragment.newInstance(pageFour))

        showSkipButton(false)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this, MainActivity::class.java))

        val editor = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(PREF_NAME, true)
        editor.apply()

        finish()
    }
}
