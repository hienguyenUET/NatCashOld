package com.example.natcash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class HomeScreen : AppCompatActivity() {
    private lateinit var textView:TextView;
    private lateinit var sharedPreferences: SharedPreferences;


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
       sharedPreferences = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        val phoneNumber:String? = sharedPreferences.getString("phoneNumber","Empty")
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