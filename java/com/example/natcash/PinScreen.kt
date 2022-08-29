package com.example.natcash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.InputEvent
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import kotlin.math.PI

class PinScreen : AppCompatActivity(), TextWatcher {

    private lateinit var sharedPreferences: SharedPreferences;
    private lateinit var pinCodeLayout: ConstraintLayout;
    private var currentPinNumber: String = String();

    //    private val PIN_CODE: String = "1234"
    private val pinCode: ArrayList<EditText> = ArrayList(4)
    private lateinit var nameField: TextView;
    private lateinit var pinCodeInput: String;

    companion object {
        const val NUM_OF_DIGITS = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_screen)

        sharedPreferences = getSharedPreferences("com.example.natcash", MODE_PRIVATE)

        getPhoneNumber()
        pinCodeLayout = findViewById(R.id.pin_code_layout)

        for (index in 0 until pinCodeLayout.childCount) {
            val view: View = pinCodeLayout.getChildAt(index)
            if (view is EditText) {
                pinCode.add(index, view)
                pinCode[index].addTextChangedListener(this)
            }
        }

        // check pin code input field


    }

    private fun getPhoneNumber() {
        val phoneNumber: String? = sharedPreferences.getString("phoneNumber", "Empty")
        nameField = findViewById(R.id.name)
        nameField.text = phoneNumber
    }

    override fun afterTextChanged(s: Editable) {
        (0 until pinCode.size).forEach { i ->
            val pinCodeAfterType: String = testCodeValidity().filter { !it.isWhitespace() }
            if (pinCodeAfterType.length == NUM_OF_DIGITS) {
                verifyCode(testCodeValidity())
            }
            if (s == pinCode[i].editableText) {
                // more than 1 char
                if (s.isBlank()) {
                    return
                }
                if (s.length >= 2) {
                    val secondDigit = s.toString().substring(s.length - 1, s.length) //get 2nd digit
                    if (secondDigit != currentPinNumber) {
                        pinCode[i].setText(secondDigit)
                    } else {
                        pinCode[i].setText(s.toString().substring(0, s.length - 1))
                    }
                } else if (i != pinCode.size - 1) {
                    pinCode[i + 1].requestFocus()
                    pinCode[i + 1].setSelection(pinCode[i + 1].length())
                    return
                }
            }
        }
    }


    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        currentPinNumber = s.toString()
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    private fun testCodeValidity(): String {
        var verificationCode = ""
        for (j in pinCode.indices) {
            val digit = pinCode[j].text.toString().trim { it <= ' ' }
            verificationCode += digit

        }
        if (verificationCode.trim { it <= ' ' }.length == NUM_OF_DIGITS) {
            return verificationCode
        }
        return ""
    }

    private fun verifyCode(verificationCode: String) {

        enableCodeEditTexts(false)
        setDelay(verificationCode)
    }

    private fun enableCodeEditTexts(enable: Boolean) {

        for (i in 0 until pinCode.size)
            pinCode[i].isEnabled = enable
    }

    // set shaking animation for pin code when wrong
    private fun setShakeAnimation() {
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.shake)

        for (i in 0 until pinCode.size) {
            pinCode[i].startAnimation(shake)
        }
    }

    private fun setDelay(verificationCode: String) {
        val userPinCode:String = sharedPreferences.getString("pinCode", "Empty!")!!;
        Handler(Looper.getMainLooper()).postDelayed({
            if (verificationCode == userPinCode) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            } else {
                for (i in 0 until pinCode.size) {
                    pinCode[i].text = null
                }
                enableCodeEditTexts(true)
                setShakeAnimation()
            }
        }, 2000)
    }

}