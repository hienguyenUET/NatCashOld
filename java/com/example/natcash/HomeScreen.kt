package com.example.natcash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.natcash.adapter.PromotionAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeScreen : AppCompatActivity() {
    private lateinit var textView: TextView;
    private lateinit var sharedPreferences: SharedPreferences;

    private lateinit var hotDealRecyclerView: RecyclerView
    private lateinit var bigVoucherRecyclerView: RecyclerView
    private lateinit var promotionRecyclerView: RecyclerView

    private val hotDeal: ArrayList<PromotionSection> = ArrayList();
    private val bigVoucher: ArrayList<PromotionSection> = ArrayList();
    private val promotion: ArrayList<PromotionSection> = ArrayList();

    private lateinit var hotDealAdapter: PromotionAdapter
    private lateinit var bigVoucherAdapter: PromotionAdapter
    private lateinit var promotionAdapter: PromotionAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        getPhoneNumber()
        this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // set up view for hot deal section
        hotDealRecyclerView = findViewById(R.id.hot_deal_view)
        hotDealAdapter = PromotionAdapter(this, hotDeal)
        hotDealRecyclerView.adapter = hotDealAdapter
        hotDealRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        setUpHotDeals();


        // set up for big voucher section
        bigVoucherRecyclerView = findViewById(R.id.big_voucher_rv)
        bigVoucherAdapter = PromotionAdapter(this, bigVoucher)
        bigVoucherRecyclerView.adapter = bigVoucherAdapter
        bigVoucherRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        setUpBigVoucher()

        // set up for promotion section
        promotionRecyclerView = findViewById(R.id.promotion_rv)
        promotionAdapter = PromotionAdapter(this, promotion)
        promotionRecyclerView.adapter = promotionAdapter
        promotionRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        setUpPromotion()
    }



    private fun getPhoneNumber() {
        sharedPreferences = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        val phoneNumber: String? = sharedPreferences.getString("phoneNumber", "Empty")
        textView = findViewById(R.id.phoneNumber)
        textView.text = phoneNumber
    }

    fun logout(view: View) {
        sharedPreferences = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        sharedPreferences.edit().remove("phoneNumber").apply();
        val intent = Intent(this, LoginScreen::class.java)
        startActivity(intent)
    }

    private fun setUpHotDeals() {
        val description = resources.getStringArray(R.array.promotion)
        val image = arrayOf(
            R.drawable.hotdeal_1,
            R.drawable.hot_deal_2,
            R.drawable.hot_deal_3,
            R.drawable.hot_deal_4,
            R.drawable.hot_deal_5
        )

        for (i in description.indices) {
            hotDeal.add(PromotionSection(image[i], description[i].toString()))
        }
    }

    private fun setUpBigVoucher() {
        val description = resources.getStringArray(R.array.promotion)
        val image = arrayOf(
            R.drawable.big_voucher_1,
            R.drawable.hot_deal_2,
            R.drawable.hot_deal_3,
            R.drawable.hot_deal_4,
            R.drawable.hot_deal_5
        )

        for (i in description.indices) {
            bigVoucher.add(PromotionSection(image[i], description[i].toString()))
        }
    }

    private fun setUpPromotion() {
        val description = resources.getStringArray(R.array.promotion)
        val image = arrayOf(
            R.drawable.promotion_1,
            R.drawable.hot_deal_2,
            R.drawable.hot_deal_3,
            R.drawable.hot_deal_4,
            R.drawable.hot_deal_5
        )

        for (i in description.indices) {
            promotion.add(PromotionSection(image[i], description[i].toString()))
        }
    }

}


