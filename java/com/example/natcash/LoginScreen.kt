package com.example.natcash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.natcash.account.TemporaryAccount

class LoginScreen : AppCompatActivity() {
    private lateinit var pref: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        val firstAccount = TemporaryAccount("Nguyen Chi Hien", "0971792638", "0987", 45000000)
        val secondAccount = TemporaryAccount("Nguyen Chuc An", "0866833188", "4235", 34000000)
        val thirdAccount = TemporaryAccount("Nguyen Duc Hiep", "0918231273", "4214", 45230000)

        val phoneNum: EditText = findViewById(R.id.inputPhoneNumber)
        val phoneNumber: String = phoneNum.text.toString()

        pref = getSharedPreferences("com.example.natcash", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()

        editor.putString("phoneNumber", firstAccount.phoneNumber)
        editor.putString("name", firstAccount.name)
        editor.putString("pinCode", firstAccount.pinCode)
        editor.putInt("balance", firstAccount.balance)

        // check if user phone is correct
        if (isCorrectPhoneNumber(phoneNumber, firstAccount.phoneNumber)) {
            val intent = Intent(this, PinScreen::class.java)
            startActivity(intent)
        }
        editor.apply()
    }

}

private fun isCorrectPhoneNumber(phoneNumberInput: String, phoneNumberForCheck: String): Boolean {
    if (phoneNumberInput == phoneNumberForCheck) {
        return true
    }
    return false
}