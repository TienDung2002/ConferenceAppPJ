package com.example.eventsconferencespj.log

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.eventsconferencespj.R

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val startFrag = inflater.inflate(R.layout.fragment_start, container, false)
        val button = startFrag.findViewById<Button>(R.id.button)

        button.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_logIn_Fragment)
        }

        return startFrag
    }

}