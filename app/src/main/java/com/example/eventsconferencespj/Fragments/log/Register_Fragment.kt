package com.example.eventsconferencespj.Fragments.log

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eventsconferencespj.R
import com.google.android.material.textfield.TextInputLayout

class Register_Fragment : Fragment() {
    private var isShowPassword_1 = false
    private var isShowPassword_2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val registerFrag = inflater.inflate(R.layout.fragment_register, container, false)
        val button = registerFrag.findViewById<Button>(R.id.finishedRegister_Btn)
        val goToLogIn_Btn = registerFrag.findViewById<TextView>(R.id.goToLogin)
        // 3 trường EditText
        val emailET = registerFrag.findViewById<EditText>(R.id.ET_email_register)
        val passET_1 = registerFrag.findViewById<EditText>(R.id.regisPass_1)
        val passET_2 = registerFrag.findViewById<EditText>(R.id.regisPass_2)
        // show pass icon
        val passwordToggleBtn_1 = registerFrag.findViewById<ImageButton>(R.id.ShowPassBtn_1)
        val passwordToggleBtn_2 = registerFrag.findViewById<ImageButton>(R.id.ShowPassBtn_2)

        val emailTextInputLayout = registerFrag.findViewById<TextInputLayout>(R.id.emailTextInputLayout)
        val passwordTextInputLayout_1 = registerFrag.findViewById<TextInputLayout>(R.id.passwordTextInputLayout_1)
        val passwordTextInputLayout_2 = registerFrag.findViewById<TextInputLayout>(R.id.passwordTextInputLayout_2)


        goToLogIn_Btn.setOnClickListener{
            findNavController().navigate(R.id.action_register_Fragment_to_logIn_Fragment)
        }

        // Check trường trống
        button.setOnClickListener{
            val email = emailET.text.toString()
            val password_1 = passET_1.text.toString()
            val password_2 = passET_2.text.toString()

            if (email.isEmpty()) emailTextInputLayout.error = "Không được để trống email" else emailTextInputLayout.error = null
            if (password_1.isEmpty()) passwordTextInputLayout_1.error = "Không được để trống mật khẩu" else passwordTextInputLayout_1.error = null
            if (password_2.isEmpty()) passwordTextInputLayout_2.error = "Không đợc để trống nhập lại mật khẩu" else passwordTextInputLayout_2.error = null

            if(email.isNotEmpty() && password_1.isNotEmpty() && password_2.isNotEmpty()) {
                Toast.makeText(context, "Đăng ký thành công, trở lại đăng nhập!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_register_Fragment_to_logIn_Fragment)
            }
        }

        // show/hide password 1
        passwordToggleBtn_1.setOnClickListener {
            // Đảm bảo rằng trường mật khẩu sẽ luôn hiển thị ngay lần click đầu tiên.
            if (!isShowPassword_1) {
                isShowPassword_1 = true
                passET_1.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordToggleBtn_1.setImageResource(R.drawable.eye_slash)
                passET_1.setSelection(passET_1.text.length)
                passET_1.requestFocus()
            } else {
                isShowPassword_1  = false
                if (isShowPassword_1) {
                    // Pass đang ẩn thì hiện
                    passET_1.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    passwordToggleBtn_1.setImageResource(R.drawable.eye_slash)
                } else {
                    // Pass đang hiện thì ẩn
                    passET_1.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    passwordToggleBtn_1.setImageResource(R.drawable.eye_open)
                }
                passET_1.setSelection(passET_1.text.length)
            }
        }

        // show/hide password 1
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

        return registerFrag
    }
}