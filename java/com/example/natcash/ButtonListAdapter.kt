package com.example.natcash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ButtonListAdapter(
    private val btnList: ArrayList<ButtonInfo>
) :
    RecyclerView.Adapter<ButtonListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btn: ImageButton = itemView.findViewById(R.id.serviceButton)
        val btnDescription: TextView = itemView.findViewById(R.id.btnDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.btn_recycleview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn.setBackgroundResource(btnList[position].background)
        holder.btn.setImageResource(btnList[position].imageResource)
        holder.btnDescription.text = btnList[position].textDescription
    }

    override fun getItemCount(): Int {
        return btnList.size;
    }

}
