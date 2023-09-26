package com.example.eventsconferencespj.log

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.eventsconferencespj.R
import com.google.android.material.textfield.TextInputLayout

class LogIn_Fragment : Fragment() {
    private var isShowPassword = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginFrag = inflater.inflate(R.layout.fragment_log_in, container, false)
        val goToHomeScreen = loginFrag.findViewById<Button>(R.id.finishedLogin_Btn)
        val goToRegister_Btn = loginFrag.findViewById<TextView>(R.id.goToRegister)
        // trường EditText
        val emailEditText = loginFrag.findViewById<EditText>(R.id.ET_email_login)
        val passwordEditText = loginFrag.findViewById<EditText>(R.id.logPass)
        // icon show/hide pass
        val passwordToggleBtn = loginFrag.findViewById<ImageButton>(R.id.ShowPassBtn)


        val emailTextInputLayout = loginFrag.findViewById<TextInputLayout>(R.id.emailTextInputLayout)
        val passwordTextInputLayout = loginFrag.findViewById<TextInputLayout>(R.id.passwordTextInputLayout)

        goToRegister_Btn.setOnClickListener {
            findNavController().navigate(R.id.action_logIn_Fragment_to_register_Fragment)
        }

        // Truy cập vào home screen
        goToHomeScreen.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty()) {
                emailTextInputLayout.error = "Vui lòng nhập email"
            } else {
                emailTextInputLayout.error = null
            }

            if (password.isEmpty()) {
                passwordTextInputLayout.error = "Vui lòng nhập mật khẩu"
            } else {
                passwordTextInputLayout.error = null
            }

            if(email.isNotEmpty() && password.isNotEmpty()) {
                findNavController().navigate(R.id.action_logIn_Fragment_to_home2)
            }
        }

        // Check trường trống
        passwordToggleBtn.setOnClickListener {
            // Đảm bảo rằng trường mật khẩu sẽ luôn hiển thị ngay lần click đầu tiên.
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

        return loginFrag
    }
}