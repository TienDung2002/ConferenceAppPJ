package com.example.eventsconferencespj.Activities.Log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.Activities.Location.Location
import com.example.eventsconferencespj.MySQL.DatabaseHelper.DbHelper
import com.example.eventsconferencespj.PreventDoubleClick
import com.example.eventsconferencespj.R
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {
    private var isShowPassword = false
    private lateinit var databaseHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goToHomeScreen = findViewById<Button>(R.id.finishedLogin_Btn)
        val goToRegisterBtn = findViewById<TextView>(R.id.goToRegister)
        // Trường EditText
        val emailEditText = findViewById<EditText>(R.id.ET_email_login)
        val passwordEditText = findViewById<EditText>(R.id.logPass)
        // Icon show/hide pass
        val passwordToggleBtn = findViewById<ImageButton>(R.id.ShowPassBtn)
        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.emailTextInputLayout)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.passwordTextInputLayout)

        databaseHelper = DbHelper(this)

        goToRegisterBtn.setOnClickListener {
            if (PreventDoubleClick.checkClick()) {
                // Thực hiện chuyển đến màn hình đăng ký
                val intent = Intent(this, Register::class.java)
                startActivity(intent)
            }
        }

        // Truy cập vào home screen, check trường trống
        goToHomeScreen.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || !isEmailValid(email)) {
                emailTextInputLayout.error = "Email trống hoặc không hợp lệ!"
            } else {
                emailTextInputLayout.error = null
            }

            if (password.isEmpty()) {
                passwordTextInputLayout.error = "Nhập mật khẩu"
            } else {
                passwordTextInputLayout.error = null
            }

            if ((email.isNotEmpty() && isEmailValid(email)) && password.isNotEmpty()) {
                val checklogin = databaseHelper.CheckLogin(email, password)
                // Chuyển đến màn hình chính
                if (checklogin) {
                    // Thực hiện chuyển đến màn hình đăng ký
                    if (PreventDoubleClick.checkClick()) {
                        val intent = Intent(this, Home_Screen::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            // test
//                if (PreventDoubleClick.checkClick()) {
//                    val intent = Intent(this, Home_Screen::class.java)
//                    startActivity(intent)
//                    finish()
//                }
        }

        // show/hide pass
        passwordToggleBtn.setOnClickListener {
            if (!isShowPassword) {
                isShowPassword = true
                passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordToggleBtn.setImageResource(R.drawable.eye_slash)
                passwordEditText.setSelection(passwordEditText.text.length)
                passwordEditText.requestFocus()
            } else {
                isShowPassword = false
                if (isShowPassword) {
                    // Pass đang ẩn thì hiện
                    passwordEditText.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    passwordToggleBtn.setImageResource(R.drawable.eye_slash)
                } else {
                    // Pass đang hiện thì ẩn
                    passwordEditText.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    passwordToggleBtn.setImageResource(R.drawable.eye_open)
                }
                // Di chuyển con trỏ đến kí tự cuối của password => trải nghiệm tốt hơn
                passwordEditText.setSelection(passwordEditText.text.length)
            }
        }
    }

    // check định dạng email hợp lệ
    private fun isEmailValid(email: String): Boolean {
        // Sử dụng mẫu sẵn có cho email
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}