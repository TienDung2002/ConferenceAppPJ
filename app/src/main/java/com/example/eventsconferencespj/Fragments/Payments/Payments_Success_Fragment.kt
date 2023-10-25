package com.example.eventsconferencespj.Fragments.Payments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.eventsconferencespj.Activities.Conf_Detail.ConfeDetail
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.R


class Payments_Success_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val popup = inflater.inflate(R.layout.payments_success_popup, container, false)
        val buttonFinish = popup.findViewById<Button>(R.id.finishBtn)
        buttonFinish.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            val intent = Intent(activity, Home_Screen::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        return popup
    }
}