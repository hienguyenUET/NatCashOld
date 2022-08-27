package com.example.natcash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.natcash.OnBoardingItem
import com.example.natcash.R

class OnBoardingAdapter(onBoardingItems: ArrayList<OnBoardingItem>) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val onBoardingItems:ArrayList<OnBoardingItem>

    init {
        this.onBoardingItems = onBoardingItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_on_boarding, parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.image.setImageResource(onBoardingItems[position].image)
        holder.title.text = onBoardingItems[position].title
        holder.description.text = onBoardingItems[position].description
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    class OnBoardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image:ImageView
        val title:TextView
        val description:TextView

        init {
            image = itemView.findViewById(R.id.onboarding_image)
            title = itemView.findViewById(R.id.onboarding_title)
            description = itemView.findViewById(R.id.onboarding_description)
        }
    }
}