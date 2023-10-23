package com.example.eventsconferencespj.Activities.Payments

import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.eventsconferencespj.Activities.Conf_Detail.ConfeDetail
import com.example.eventsconferencespj.Fragments.Payments.Payments_Cash_Fragment
import com.example.eventsconferencespj.Fragments.Payments.Payments_Success_Fragment
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityPaymentsBinding
import java.util.Locale

class Payments : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Confe detail
        val bundle: Bundle? = intent.extras
        val nameConf = bundle?.getString("name")
        val addressConf = bundle?.getString("address")
        val priceConf = bundle?.getInt("price")
        val required = bundle?.getInt("required")
        val numberOfSeatConf = bundle?.getInt("seat")
        val rating = bundle?.getDouble("rating")
        val imageConf = bundle?.getInt("image")

        binding.backButton.setOnClickListener {
            val intent = Intent(this@Payments, ConfeDetail::class.java)
            intent.putExtra("address", addressConf)
            intent.putExtra("price", priceConf)
            intent.putExtra("required", required)
            intent.putExtra("seat", numberOfSeatConf)
            intent.putExtra("rating", rating)
            intent.putExtra("image", imageConf)
            startActivity(intent)
        }

        binding.goToPaymentBtn.setOnClickListener {
            val fragment = if (binding.cashBtn.isChecked) {
                // Nếu cashBtn được chọn, mở fragment cashPopup
                Payments_Cash_Fragment()
            } else {
                Payments_Success_Fragment()
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.successFragPopup, fragment)
            transaction.addToBackStack(null) // Thêm Fragment vào back stack
            transaction.commit()
        }

        binding.cashBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            // Nếu người dùng chọn cashBtn => k cho điền số và ngược lại
            if (isChecked) {
                binding.cardNumberTextInput.visibility = View.GONE
                binding.goToPaymentBtn.text = "Đặt hẹn"
            } else {
                binding.cardNumberTextInput.visibility = View.VISIBLE
                binding.goToPaymentBtn.text = "Thanh toán"
            }
            !isChecked
        }

        val priceFormatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
        binding.confNamePayment.text = nameConf
        binding.pricePerDay.text = priceFormatter.format(priceConf).toString()
        binding.priceDetailNum.text = priceFormatter.format(priceConf).toString()
        binding.depositNum.text = priceFormatter.format(required).toString()
        if (imageConf != null) binding.confeImg.setImageResource(imageConf)

        // Tổng thanh toán
        if (required != null && priceConf != null && required >= 0) {
            val totalValue = if (required == 0) priceConf
            else {
                priceConf * (required.toDouble() / 100.0)
            }
            binding.totalNum.text = priceFormatter.format(totalValue).toString()
        }
    }
}