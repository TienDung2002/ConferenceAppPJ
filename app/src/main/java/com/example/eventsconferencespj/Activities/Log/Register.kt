package com.example.eventsconferencespj.Activities.Log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.eventsconferencespj.R
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {
    private var isShowPassword_1 = false
    private var isShowPassword_2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button = findViewById<Button>(R.id.finishedRegister_Btn)
        val goToLogIn_Btn = findViewById<TextView>(R.id.goToLogin)
        // Trường EditText
        val emailET = findViewById<EditText>(R.id.ET_email_register)
        val passET_1 = findViewById<EditText>(R.id.regisPass_1)
        val passET_2 = findViewById<EditText>(R.id.regisPass_2)
        // Icon show/hide pass
        val passwordToggleBtn_1 = findViewById<ImageButton>(R.id.ShowPassBtn_1)
        val passwordToggleBtn_2 = findViewById<ImageButton>(R.id.ShowPassBtn_2)

        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.emailTextInputLayout)
        val passwordTextInputLayout_1 = findViewById<TextInputLayout>(R.id.passwordTextInputLayout_1)
        val passwordTextInputLayout_2 = findViewById<TextInputLayout>(R.id.passwordTextInputLayout_2)

        goToLogIn_Btn.setOnClickListener {
            // Thực hiện chuyển đến màn hình đăng nhập (Login)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val email = emailET.text.toString()
            val password_1 = passET_1.text.toString()
            val password_2 = passET_2.text.toString()

            if (email.isEmpty()) emailTextInputLayout.error = "Không được để trống email" else emailTextInputLayout.error = null
            if (password_1.isEmpty()) passwordTextInputLayout_1.error = "Không được để trống mật khẩu" else passwordTextInputLayout_1.error = null
            if (password_2.isEmpty()) passwordTextInputLayout_2.error = "Không đợc để trống nhập lại mật khẩu" else passwordTextInputLayout_2.error = null

            if (email.isNotEmpty() && password_1.isNotEmpty() && password_2.isNotEmpty()) {
                Toast.makeText(this, "Đăng ký thành công, trở lại đăng nhập!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Show/Hide password 1
        passwordToggleBtn_1.setOnClickListener {
            if (!isShowPassword_1) {
                isShowPassword_1 = true
                passET_1.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordToggleBtn_1.setImageResource(R.drawable.eye_slash)
                passET_1.setSelection(passET_1.text.length)
                passET_1.requestFocus()
            } else {
                isShowPassword_1 = false
                if (isShowPassword_1) {
                    passET_1.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    passwordToggleBtn_1.setImageResource(R.drawable.eye_slash)
                } else {
                    passET_1.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    passwordToggleBtn_1.setImageResource(R.drawable.eye_open)
                }
                passET_1.setSelection(passET_1.text.length)
            }
        }

        // Show/Hide password 2
        passwordToggleBtn_2.setOnClickListener {
            if (!isShowPassword_2) {
                isShowPassword_2 = true
                passET_2.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordToggleBtn_2.setImageResource(R.drawable.eye_slash)
                passET_2.setSelection(passET_2.text.length)
                passET_2.requestFocus()
            } else {
                isShowPassword_2 = false
                if (isShowPassword_2) {
                    passET_2.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    passwordToggleBtn_2.setImageResource(R.drawable.eye_slash)
                } else {
                    passET_2.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    passwordToggleBtn_2.setImageResource(R.drawable.eye_open)
                }
                passET_2.setSelection(passET_2.text.length)
            }
        }
    }
}
