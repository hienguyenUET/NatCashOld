package com.example.natcash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeScreen : AppCompatActivity() {
    private lateinit var textView: TextView;
    private lateinit var sharedPreferences: SharedPreferences;
//    private var gridLayout: GridLayoutManager = R.id.homeButtonRecyclerView
    private var adapter: ButtonListAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var btnList = ArrayList<ButtonInfo>()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        getPhoneNumber()

        //define recyclerview
//        recyclerView = findViewById(R.id.homeButtonRecyclerView)
//        gridLayout = GridLayoutManager(this, 2)
//        recyclerView.apply {gridLayout = GridLayoutManager(applicationContext, 3) }
//        getButton()

    }

    private fun getPhoneNumber() {
        sharedPreferences = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        val phoneNumber: String? = sharedPreferences.getString("phoneNumber", "Empty")
        println(phoneNumber)
        if (sharedPreferences.contains("phoneNumber")) println("yes")
        textView = findViewById(R.id.phoneNumber)
        textView.text = phoneNumber
    }

    fun logout(view: View) {
        sharedPreferences = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        sharedPreferences.edit().remove("phoneNumber").apply();
        val intent = Intent(this, LoginScreen::class.java)
        startActivity(intent)
    }

}