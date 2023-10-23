package com.example.eventsconferencespj.Activities.Log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.InputType
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.eventsconferencespj.Activities.Payments.Payments
import com.example.eventsconferencespj.MySQL.DatabaseHelper.DbHelper
import com.example.eventsconferencespj.PreventDoubleClick
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityRegisterBinding
import com.example.eventsconferencespj.databinding.ActivityUserDetailBinding
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {
    private var isShowPassword_1 = false
    private var isShowPassword_2 = false
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var databaseHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Trường EditText
        val emailET = binding.ETEmailRegister
        val passET_1 = binding.regisPass1
        val passET_2 = binding.regisPass2

        val emailTextInputLayout = binding.emailTextInputLayout
        val passwordTextInputLayout_1 = binding.passwordTextInputLayout1
        val passwordTextInputLayout_2 = binding.passwordTextInputLayout1

        databaseHelper = DbHelper(this)

        binding.goToLogin.setOnClickListener {
            if (PreventDoubleClick.checkClick()) {
                // Thực hiện chuyển đến màn hình đăng nhập (Login)
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.finishedRegisterBtn.setOnClickListener {
            val email = emailET.text.toString()
            val password_1 = passET_1.text.toString()
            val password_2 = passET_2.text.toString()

            if (email.isEmpty() || !isEmailValid(email)) emailTextInputLayout.error = "Email trống hoặc không hợp lệ" else emailTextInputLayout.error = null
            if (password_1.isEmpty()) passwordTextInputLayout_1.error = "Nhập mật khẩu" else passwordTextInputLayout_1.error = null
            if (password_2.isEmpty()) passwordTextInputLayout_2.error = "Nhập lại mật khẩu" else passwordTextInputLayout_2.error = null
            if (password_1 !== password_2) Toast.makeText(applicationContext, "Nhập lại mật khẩu không khớp", Toast.LENGTH_SHORT).show()

            if ((email.isNotEmpty() && isEmailValid(email)) && password_1.isNotEmpty() && password_2.isNotEmpty() && password_1 == password_2) {
                val checkemail = databaseHelper.CheckEmail(email)
                if (checkemail) {
                    val insert = databaseHelper.Insert(email, password_1)
                    if (insert) {
                        Toast.makeText(this, "Đăng ký thành công, trở lại đăng nhập!", Toast.LENGTH_SHORT).show()
                        // Ngăn bấm nút 2 lần
                        if (PreventDoubleClick.checkClick()) {
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Toast.makeText(this, "Thêm dữ liệu thất bại", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Show/Hide password 1
        binding.ShowPassBtn1.setOnClickListener {
            showOrHidePass_1()
        }

        // Show/Hide password 2
        binding.ShowPassBtn2.setOnClickListener {
            showOrHidePass_2()
        }
    }

    // check định dạng email hợp lệ
    private fun isEmailValid(email: String): Boolean {
        // Sử dụng mẫu sẵn có cho email
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun showOrHidePass_1(){
        if (!isShowPassword_1) {
            isShowPassword_1 = true
            binding.regisPass1.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ShowPassBtn1.setImageResource(R.drawable.eye_slash)
            binding.regisPass1.setSelection(binding.regisPass1.text!!.length)
            binding.regisPass1.requestFocus()
        } else {
            isShowPassword_1 = false
            if (isShowPassword_1) {
                binding.regisPass1.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ShowPassBtn1.setImageResource(R.drawable.eye_slash)
            } else {
                binding.regisPass1.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ShowPassBtn1.setImageResource(R.drawable.eye_open)
            }
            binding.regisPass1.text?.let { binding.regisPass1.text!!.length }
        }
    }

    private fun showOrHidePass_2(){
        if (!isShowPassword_2) {
            isShowPassword_2 = true
            binding.regisPass2.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.ShowPassBtn2.setImageResource(R.drawable.eye_slash)
            binding.regisPass2.setSelection(binding.regisPass2.text!!.length)
            binding.regisPass2.requestFocus()
        } else {
            isShowPassword_2 = false
            if (isShowPassword_2) {
                binding.regisPass2.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ShowPassBtn2.setImageResource(R.drawable.eye_slash)
            } else {
                binding.regisPass2.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ShowPassBtn2.setImageResource(R.drawable.eye_open)
            }
            binding.regisPass2.setSelection(binding.regisPass2.text!!.length)
        }
    }
}
