package com.example.eventsconferencespj.Fragments.Location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.eventsconferencespj.R

class Location_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_, container, false)
        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            // Xử lý khi nút trở lại được nhấn
            activity?.supportFragmentManager?.popBackStack()
        }
        return view
    }
}