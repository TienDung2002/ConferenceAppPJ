package com.example.eventsconferencespj.Activities.Conf_Detail

import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.Activities.Payments.Payments
import com.example.eventsconferencespj.Activities.Users.User_Detail
import com.example.eventsconferencespj.PreventDoubleClick
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityConfeDetailBinding
import java.util.Locale

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
            if (PreventDoubleClick.checkClick()) {
                val intent = Intent(this@ConfeDetail, Payments::class.java)
                startActivity(intent)
            }
        }

        // chưa được
        var checkHeart = false
        binding.addToWishList.setOnClickListener{
            checkHeart = !checkHeart
            if (checkHeart) {
                binding.addToWishList.setImageResource(R.drawable.toolbar_heart_icon_3)
            }
            binding.addToWishList.setImageResource(R.drawable.toolbar_heart_icon)
        }

        // nhận dũ liệu từ home-screen
        val bundle : Bundle? = intent.extras
        val nameConf = bundle?.getString("name")
        val addressConf = bundle?.getString("address")
        val priceConf = bundle?.getInt("price")
        val numberOfSeatConf = bundle?.getInt("seat")
        val imageConf = bundle?.getInt("image")

        // format tiền Việt
        val priceFormatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))

        binding.confNameDetail.text = nameConf
        binding.confAddressDetail.text = addressConf
        binding.pricePerDay.text = priceFormatter.format(priceConf).toString()
        binding.numberOfSeat.text = numberOfSeatConf.toString()
        if (imageConf != null) binding.imageView.setImageResource(imageConf)

    }
}