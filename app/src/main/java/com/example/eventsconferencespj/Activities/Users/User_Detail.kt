package com.example.eventsconferencespj.Activities.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityUserDetailBinding

class User_Detail : AppCompatActivity() {
    private var isShowPassword = false
    private lateinit var binding : ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, Home_Screen::class.java)
            startActivity(intent)
            finish()
        }

        // show/hide password
        binding.ShowPassBtn.setOnClickListener {
            showOrHidePass()
        }
        // nút thay đổi thông tin user
        binding.changeDataUser.setOnClickListener {

        }
    }

    fun showOrHidePass(){
        // Đảm bảo rằng trường mật khẩu sẽ luôn hiển thị ngay lần click đầu tiên.
        if (!isShowPassword) {
            isShowPassword = true
            binding.passDetail.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ShowPassBtn.setImageResource(R.drawable.eye_slash)
            binding.passDetail.setSelection(binding.passDetail.text.length)
            binding.passDetail.requestFocus()
        } else {
            isShowPassword = false
            if (isShowPassword) {
                // Pass đang ẩn thì hiện
                binding.passDetail.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ShowPassBtn.setImageResource(R.drawable.eye_slash)
            } else {
                // Pass đang hiện thì ẩn
                binding.passDetail.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ShowPassBtn.setImageResource(R.drawable.eye_open)
            }
            binding.passDetail.setSelection(binding.passDetail.text.length)
        }
    }
}