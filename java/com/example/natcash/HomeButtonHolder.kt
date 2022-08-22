package com.example.natcash

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeButtonHolder(private val imageButton: ImageButton, private val textView: TextView,
                       itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bindingButton(buttonInfo: ButtonInfo){
        imageButton.setImageResource(buttonInfo.imageResource)
        imageButton.setBackgroundResource(buttonInfo.background)
        textView.text = buttonInfo.textDescription
    }
}