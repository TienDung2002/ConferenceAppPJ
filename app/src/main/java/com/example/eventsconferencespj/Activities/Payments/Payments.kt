package com.example.eventsconferencespj.Activities.Payments

import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.eventsconferencespj.Activities.Conf_Detail.ConfeDetail
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
        val priceConf = bundle?.getInt("price")?: 0
        val required = bundle?.getInt("required")?: 0
        val imageConf = bundle?.getInt("image")

        binding.backButton.setOnClickListener {
            val intent = Intent(this@Payments, ConfeDetail::class.java)
            startActivity(intent)
        }

//        binding.goToPaymentBtn.setOnClickListener {
//            val fragment = Payments_Success_Fragment() // Tạo một instance của PaymentSuccessFragment
//
//            val fragmentManager = supportFragmentManager // Lấy FragmentManager của Activity
//            val transaction = fragmentManager.beginTransaction()
//            transaction.replace(R.id.successFragPopup, fragment) // R.id.fragment_container là ID của layout chứa Fragment
//            transaction.addToBackStack(null) // Để thêm Fragment vào back stack (tùy chọn)
//            transaction.commit() // Hoàn thành giao dịch
//        }

        val priceFormatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
        binding.confNamePayment.text = nameConf
        binding.pricePerDay.text = priceFormatter.format(priceConf).toString()
        binding.priceDetailNum.text = priceFormatter.format(priceConf).toString()
        binding.depositNum.text = priceFormatter.format(required).toString()
        if (imageConf != null) binding.confeImg.setImageResource(imageConf)
        // Tổng thanh toán
        if (required != 0) {
            val totalValue = (priceConf * (required.toDouble() / 100.0))
            binding.totalNum.text = priceFormatter.format(totalValue).toString()
        }
        Log.d("priceConf", priceConf.toString())
        binding.totalNum.text = priceFormatter.format(priceConf).toString()
    }
}