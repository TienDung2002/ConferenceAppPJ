package com.example.eventsconferencespj.Activities.Home

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsconferencespj.R


class HomeAdapter(private val list: List<ConfeData>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvConfName: TextView
        val ratingConf: RatingBar
        val price: TextView
        val img: ImageView

        init {
            tvConfName = view.findViewById(R.id.confNameHome)
            ratingConf = view.findViewById(R.id.ratingID)
            price = view.findViewById(R.id.pricePerDay)
            img = view.findViewById(R.id.conf_img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conference_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder.tvConfName.text = list[position].confName
        holder.ratingConf.rating = list[position].ratingStar.toFloat()
        holder.price.text = list[position].price.toString()
        holder.img.setImageResource(R.drawable.event_room_demo)
    }

    override fun getItemCount():Int = list.size
}