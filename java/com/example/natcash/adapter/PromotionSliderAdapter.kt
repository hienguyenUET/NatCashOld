package com.example.natcash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.natcash.R
import com.smarteist.autoimageslider.SliderViewAdapter

class PromotionSliderAdapter(imageViewList: Array<Int>) : SliderViewAdapter<PromotionSliderAdapter.MyViewHolder>() {

    private var imageViewList : Array<Int>

    init {
        this.imageViewList = imageViewList
    }

    class MyViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {
        val image : ImageView = itemView!!.findViewById(R.id.promotionSlider)
    }


    override fun getCount(): Int {
        return imageViewList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): MyViewHolder {
       val view:View = LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder?, position: Int) {
        viewHolder!!.image.setImageResource(imageViewList[position])
    }
}
