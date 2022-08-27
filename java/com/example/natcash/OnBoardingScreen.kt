package com.example.natcash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.natcash.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3

class OnBoardingScreen : AppCompatActivity() {


    private lateinit var adapter: OnBoardingAdapter
    private lateinit var circleIndicator: CircleIndicator3
    private lateinit var viewPager2: ViewPager2
    private lateinit var nextButton: Button
    private lateinit var skipButton: TextView
    private var count:Int = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        // setup next button
        nextButton = findViewById(R.id.next_button)
        skipButton = findViewById(R.id.skip_btn)
        // setup boarding items
        setUpOnBoardingItem()
        adapter = OnBoardingAdapter(setUpOnBoardingItem())
        viewPager2 = findViewById(R.id.on_boarding_view_pager)
        viewPager2.adapter = adapter


        // setup indicators for onboarding screen
        circleIndicator = findViewById(R.id.circle_indicator)
        circleIndicator.setViewPager(viewPager2)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                if (viewPager2.currentItem == 2) {
                    count = 2
                    nextButton.text = "Get started!"
                } else {
                    nextButton.text = "Next"
                    count--
                }
            }
        })


    }

    private fun setUpOnBoardingItem(): ArrayList<OnBoardingItem> {
        val images: Array<Int> = arrayOf(
            R.drawable.first_onboarding_img,
            R.drawable.second_onboarding_img,
            R.drawable.third_onboarding_img
        )
        val title: Array<String> = resources.getStringArray(R.array.on_boarding_title)
        val descriptions: Array<String> = resources.getStringArray(R.array.on_boarding_description)
        val onBoardingItem: ArrayList<OnBoardingItem> = ArrayList()

        for (i in images.indices) {
            val item = OnBoardingItem(images[i], title[i], descriptions[i])
            onBoardingItem.add(item)
        }

        return onBoardingItem
    }

    fun setSkipButton(view: View) {
        viewPager2.currentItem = 2
    }

    @SuppressLint("SetTextI18n")
    fun setNextButton(view: View) {
        count++
        if (viewPager2.currentItem < 2) {
            viewPager2.currentItem = viewPager2.currentItem + 1
        }

        // check if button is showing get started!
        if (count == 3) {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
    }
}
