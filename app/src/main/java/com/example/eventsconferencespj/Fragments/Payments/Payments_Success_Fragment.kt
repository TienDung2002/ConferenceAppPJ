package com.example.eventsconferencespj.Fragments.Payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eventsconferencespj.R


class Payments_Success_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val popup = inflater.inflate(R.layout.payments_success_popup, container, false)

        return popup
    }
}