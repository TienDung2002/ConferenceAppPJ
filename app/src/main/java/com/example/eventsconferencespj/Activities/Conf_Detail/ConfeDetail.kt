package com.example.eventsconferencespj.Activities.Conf_Detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.Activities.Payments.Payments
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityConfeDetailBinding

class ConfeDetail : AppCompatActivity() {
    lateinit var binding: ActivityConfeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this@ConfeDetail, Home_Screen::class.java)
            startActivity(intent)
            finish()
        }

        binding.goToPaymentBtn.setOnClickListener{
            val intent = Intent(this@ConfeDetail, Payments::class.java)
            startActivity(intent)
        }

        val bundle : Bundle? = intent.extras
        val nameConf = bundle?.getString("name")
        val addressConf = bundle?.getString("address")
        val priceConf = bundle?.getInt("price")
        val numberOfSeatConf = bundle?.getInt("seat")
        val imageConf = bundle?.getInt("image")

        binding.confNameDetail.text = nameConf
        binding.confAddressDetail.text = addressConf
        binding.pricePerDay.text = priceConf.toString()
        binding.numberOfSeat.text = numberOfSeatConf.toString()
        if (imageConf != null) binding.imageView.setImageResource(imageConf)

    }
}