package com.example.natcash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.natcash.PromotionSection
import com.example.natcash.R

class PromotionAdapter(context: Context, viewArray: ArrayList<PromotionSection>) :
    RecyclerView.Adapter<PromotionAdapter.MyViewHolder>() {
    private var context: Context;
    private var viewArray: ArrayList<PromotionSection>

    init {
        this.context = context
        this.viewArray = viewArray
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.hot_deal_img)
        val des: TextView = itemView.findViewById(R.id.hot_deal_description)
        val promotionLayout: ConstraintLayout = itemView.findViewById(R.id.promotion_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.promotion_activity, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(viewArray[position].image)
        holder.des.text = viewArray[position].description

        setMarginForFirstAndLast(holder.promotionLayout, position)

    }

    private fun setMarginForFirstAndLast(promotionLayout: ConstraintLayout, pos: Int) {
        val p: ViewGroup.MarginLayoutParams =
            promotionLayout.layoutParams as ViewGroup.MarginLayoutParams

        if (pos == 0) {
            p.marginStart = 40
        } else if (pos == viewArray.size - 1) {
            p.marginEnd = 40
        }
        promotionLayout.requestLayout()
    }

    override fun getItemCount(): Int {
        return viewArray.size;
    }
}