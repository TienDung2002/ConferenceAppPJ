package com.example.eventsconferencespj.Activities.Home

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.icu.text.NumberFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsconferencespj.R
import java.util.Locale


class HomeAdapter(private var list: List<ConfeData>, private val noDataImage: ImageView) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(), Filterable {
    lateinit var mListener: onItemClickListener
    private var originalData: List<ConfeData> = list
    private var filteredData: List<ConfeData> = list

    interface onItemClickListener{
        fun onItemClicked(position: Int){
        }
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class HomeViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val tvConfName: TextView
        val ratingConf: RatingBar
        val price: TextView
        val img: ImageView

        init {
            tvConfName = view.findViewById(R.id.confNameHome)
            ratingConf = view.findViewById(R.id.ratingID)
            price = view.findViewById(R.id.pricePerDay)
            img = view.findViewById(R.id.conf_img)

            view.setOnClickListener {
                listener.onItemClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conference_item, parent, false)
        return HomeViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        // Định dạng giá trị price với dấu phẩy
        val priceFormatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
        val formattedPrice = priceFormatter.format(list[position].price)

        holder.tvConfName.text = list[position].confName
        holder.ratingConf.rating = list[position].ratingStar.toFloat()
        holder.price.text = formattedPrice.toString()
        holder.img.setImageResource(list[position].image)
    }

    override fun getItemCount():Int = list.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase()
                val filteredList = if (query.isEmpty()) {
                    list
                } else {
                    list.filter { item ->
                        item.confName.lowercase().contains(query)
                    }
                }

                val filterResults = FilterResults()
                filterResults.count = filteredList.size
                filterResults.values = filteredList

                // check xem kết quả lọc
                Log.d("FilteredList", filteredList.toString())

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val filteredList = results?.values
                filteredData = if (filteredList is List<*>) {
                    filteredList.filterIsInstance<ConfeData>()
                } else {
                    emptyList()
                }
                list = filteredData
                notifyDataSetChanged()

                if (list.isEmpty()) {
                    showNoDataFound()
                } else {
                    hideNoDataFound()
                }
            }
        }
    }
    fun resetOriginalList() {
        list = originalData
        hideNoDataFound()
        notifyDataSetChanged()
    }

    fun showNoDataFound() {
        noDataImage.visibility = View.VISIBLE
    }

    fun hideNoDataFound() {
        noDataImage.visibility = View.GONE
    }
}