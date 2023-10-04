package com.example.eventsconferencespj.Fragments.ConfDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eventsconferencespj.R

class ConferenceDetail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragDetail = inflater.inflate(R.layout.conference_item_detail, container, false)

        return fragDetail
    }

}