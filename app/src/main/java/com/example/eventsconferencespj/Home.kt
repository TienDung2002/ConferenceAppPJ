package com.example.eventsconferencespj

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.eventsconferencespj.databinding.FragmentHomeBinding


class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homePage = inflater.inflate(R.layout.fragment_home, container, false)

        // Đổi màu hint của search bar
        var searchItem: SearchView? = homePage.findViewById<SearchView>(R.id.search_view)
        if (searchItem != null) searchItem.setQueryHint(Html.fromHtml("<font color = #ffffff>" + "Tìm kiếm" + "</font>"))



        return homePage
    }


}