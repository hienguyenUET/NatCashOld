package com.example.natcash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class LoginScreen : AppCompatActivity() {
    private lateinit var pref:SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        val intent = Intent(this, PinScreen::class.java)
        startActivity(intent)
        val phoneNum:EditText = findViewById(R.id.inputPhoneNumber)
        val phoneNumber:String = phoneNum.text.toString()
        pref = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        val editor:SharedPreferences.Editor = pref.edit()
        editor.putString("phoneNumber", phoneNumber)
        editor.apply()
    }

}