package com.example.eventsconferencespj.Activities.Payments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventsconferencespj.Activities.Conf_Detail.ConfeDetail
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.Fragments.Payments.Payments_Success_Fragment
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityPaymentsBinding

class Payments : AppCompatActivity() {
    lateinit var binding : ActivityPaymentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this@Payments, ConfeDetail::class.java)
            startActivity(intent)
            finish()
        }

        binding.goToPaymentBtn.setOnClickListener{
            val fragment = Payments_Success_Fragment() // Tạo một instance của PaymentSuccessFragment

            val fragmentManager = supportFragmentManager // Lấy FragmentManager của Activity
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.successFragPopup, fragment) // R.id.fragment_container là ID của layout chứa Fragment
            transaction.addToBackStack(null) // Để thêm Fragment vào back stack (tùy chọn)
            transaction.commit() // Hoàn thành giao dịch
        }
    }
}