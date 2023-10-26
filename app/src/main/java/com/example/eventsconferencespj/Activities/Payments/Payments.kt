package com.example.eventsconferencespj.Activities.Payments

import android.app.Activity
import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        val priceConf = bundle?.getInt("price")
        val required = bundle?.getInt("required")
        val imageConf = bundle?.getInt("image")
        val emailFromConfeDetail = bundle?.getString("email")

        binding.backButton.setOnClickListener {
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.goToPaymentBtn.setOnClickListener {
            val fragment: Fragment
            val transaction = supportFragmentManager.beginTransaction()
            val bundle_2 = Bundle()
            bundle_2.putString("email", emailFromConfeDetail)

            if (binding.cashBtn.isChecked) {
                // Nếu cashBtn được chọn, mở fragment cashPopup
                fragment = Payments_Cash_Fragment()
            } else {
                val cardNumber = binding.addNumCard.text.toString()
                if (cardNumber.isBlank()) {
                    Toast.makeText(this, "Vui lòng nhập số thẻ", Toast.LENGTH_SHORT).show()
                    // return@setOnClickListener dùng để thoát khỏi lambda của setOnClickListener
                    // hiểu đơn giản là nếu đk này k đáp ứng thì code bên dưới nó k dc thực thi
                    return@setOnClickListener
                }
                // nếu cardNumber được nhập thì mở fragment
                fragment = Payments_Success_Fragment()
                fragment.arguments = bundle_2
            }
            fragment.arguments = bundle_2

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