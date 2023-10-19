package com.example.eventsconferencespj.Activities.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.Activities.Log.Login
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityUserDetailBinding

class User_Detail : AppCompatActivity() {
    private var isShowPassword = false
    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, Home_Screen::class.java)
            startActivity(intent)
            finish()
        }

        // show/hide password
        binding.ShowPassBtn.setOnClickListener {
            showOrHidePass()
        }

        // Ban đầu, không cho chỉnh sửa và ẩn nút ShowPassBtn
        checkStateEdit(false)
        // nút thay đổi thông tin user
        binding.changeDataUser.setOnClickListener {
            if (binding.changeDataUser.text == "Cập nhật thông tin") {
                binding.changeDataUser.text = "Lưu thay đổi"
                // Cho phép chỉnh sửa EditText và hiện ShowPassBtn
                checkStateEdit(true)
                Toast.makeText(this, "Chọn trường muốn sửa", Toast.LENGTH_SHORT).show()
            } else {
                val name = binding.nameDetail.text.toString()
                val phone = binding.phoneDetail.text.toString()
                val email = binding.emailDetail.text.toString()
                val pass = binding.passDetail.text.toString()
                var valid = true

                if (name.isEmpty()) {
                    Toast.makeText(this, "Không để trống tên", Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (phone.isEmpty()) {
                    Toast.makeText(this, "Không để trống SĐT", Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (email.isEmpty() || !isEmailValid(email)) {
                    Toast.makeText(this, "Email trống hoặc không hợp lệ!", Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (pass.isEmpty()) {
                    Toast.makeText(this, "Không để trống mật khẩu", Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (valid) {
                    binding.changeDataUser.text = "Cập nhật thông tin"
                    // xử lý lưu thông tin vào db ở đây
                    //
                    //
                    // Ngăn chỉnh sửa EditText và ẩn ShowPassBtn
                    checkStateEdit(false)
                    Toast.makeText(this, "Lưu thành công!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Đăng xuất
        binding.logoutBtn.setOnClickListener {
            val intentUserDetail = Intent(this, Login::class.java)
            startActivity(intentUserDetail)
            Toast.makeText(this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show()
            finish()
            // dùng viewmodel để cập nhật lại thông tin cho tất cả các activity nếu đăng xuất ra và log lại tài khoản khác
        }
    }

    private fun showOrHidePass() {
        // Đảm bảo rằng trường mật khẩu sẽ luôn hiển thị ngay lần click đầu tiên.
        if (!isShowPassword) {
            isShowPassword = true
            binding.passDetail.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ShowPassBtn.setImageResource(R.drawable.eye_slash)
            binding.passDetail.setSelection(binding.passDetail.text.length)
            binding.passDetail.requestFocus()
        } else {
            isShowPassword = false
            if (isShowPassword) {
                // Pass đang ẩn thì hiện
                binding.passDetail.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ShowPassBtn.setImageResource(R.drawable.eye_slash)
            } else {
                // Pass đang hiện thì ẩn
                binding.passDetail.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ShowPassBtn.setImageResource(R.drawable.eye_open)
            }
            binding.passDetail.setSelection(binding.passDetail.text.length)
        }
    }

    private fun checkStateEdit(state: Boolean) {
        binding.nameDetail.isEnabled = state
        binding.phoneDetail.isEnabled = state
        binding.emailDetail.isEnabled = state
        binding.passDetail.isEnabled = state
        binding.ShowPassBtn.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun isEmailValid(email: String): Boolean {
        // Sử dụng mẫu sẵn có cho email
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}