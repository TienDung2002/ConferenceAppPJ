package com.example.eventsconferencespj.Activities.Conf_Detail

import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
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

        // nhận dũ liệu từ home-screen
        val bundle : Bundle? = intent.extras
        val nameConf = bundle?.getString("name")
        val addressConf = bundle?.getString("address")
        val priceConf = bundle?.getInt("price")
        val required = bundle?.getInt("required")
        val numberOfSeatConf = bundle?.getInt("seat")
        val rating = bundle?.getDouble("rating")
        val imageConf = bundle?.getInt("image")

        binding.goToPaymentBtn.setOnClickListener{
            if (PreventDoubleClick.checkClick()) {
                val intent = Intent(this@ConfeDetail, Payments::class.java)
                intent.putExtra("name", nameConf)
                intent.putExtra("address", addressConf)
                intent.putExtra("price", priceConf)
                intent.putExtra("required", required)
                intent.putExtra("seat", numberOfSeatConf)
                intent.putExtra("rating", rating)
                intent.putExtra("image", imageConf)
                startActivity(intent)
            }
        }

        var checkHeart = false
        binding.addToWishList.setOnClickListener{
            if (checkHeart) binding.addToWishList.setImageResource(R.drawable.toolbar_heart_icon)
            else binding.addToWishList.setImageResource(R.drawable.toolbar_heart_icon_3)
            checkHeart = !checkHeart
        }

        // format tiền Việt
        val priceFormatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))

        binding.confNameDetail.text = nameConf
        binding.confAddressDetail.text = addressConf

        // rating
        if (rating != null) binding.ratingID.rating = rating.toFloat()
        binding.overallPoint.text = rating.toString()

        binding.pricePerDay.text = priceFormatter.format(priceConf).toString()
        binding.numberOfSeat.text = numberOfSeatConf.toString()
        if (imageConf != null) binding.imageView.setImageResource(imageConf)
    }
}