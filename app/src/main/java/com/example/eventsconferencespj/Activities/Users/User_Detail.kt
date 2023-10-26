package com.example.eventsconferencespj.Activities.Users

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.Activities.Log.Login
import com.example.eventsconferencespj.MySQL.DatabaseHelper.DbHelper
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityUserDetailBinding

class User_Detail : AppCompatActivity() {
    private var isShowPassword = false
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var databaseHelper: DbHelper
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // khởi tạo dbHelper
        databaseHelper = DbHelper(this)
        // viewModel
        val userModel = ViewModelProvider(this).get(UserModel::class.java)

        // lấy data user từ home_screen và database
        val bundle: Bundle? = intent.extras
        val userEmail = bundle?.getString("email")
        val emailFromDB = userEmail?.let { databaseHelper.getEmail(it)} ?: "DefaultEmail@gmail.com"
        val passFromDB = userEmail?.let { databaseHelper.getPassword(it) }
        val nameFromDB = userEmail?.let { databaseHelper.getName(it) } ?: "New User"
        val phoneFromDB = userEmail?.let { databaseHelper.getPhoneNum(it) }

        //gán vào model
        userModel.email = Editable.Factory.getInstance().newEditable(emailFromDB)
        userModel.pass = Editable.Factory.getInstance().newEditable(passFromDB)
        userModel.name = Editable.Factory.getInstance().newEditable(nameFromDB)
        userModel.phone = Editable.Factory.getInstance().newEditable(phoneFromDB.toString())
        Log.d("phoneCheck", userModel.phone.toString())         // trả ra 0

        // gán vào trường tương ứng
        binding.emailDetail.text = userModel.email
        binding.passDetail.text = userModel.pass
        binding.nameDetail.text = userModel.name
        binding.phoneDetail.text = userModel.phone

        binding.backButton.setOnClickListener {
            val intent = Intent(this, Home_Screen::class.java)
            intent.putExtra("email", userModel.email.toString())
            startActivity(intent)
        }

        // show/hide password
        binding.ShowPassBtn.setOnClickListener { showOrHidePass() }

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
                if (phone.length != 10) {
                    Toast.makeText(this, "SĐT phải chứa 10 số", Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (pass.isEmpty()) {
                    Toast.makeText(this, "Không để trống mật khẩu", Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (valid) {
//                    val phone = phone.toInt()
//                    val isPhoneUpdated = databaseHelper.InsertPhone(phone)
                    val nameUpdate = databaseHelper.UpdateName(email, name)
                    val passwordUpdate = databaseHelper.updatePassword(email, pass)
                    val phoneUpdate = databaseHelper.updatePhone(email, phone.toInt())
                    val getPhone = databaseHelper.getPhoneNum(email)
                    val getName = databaseHelper.getName(email)

                    // cập nhật tên user
                    if (nameUpdate) userModel.name = Editable.Factory.getInstance().newEditable(getName)
                    // cập nhật sdt
                    if (phoneUpdate) userModel.phone = Editable.Factory.getInstance().newEditable(getPhone.toString())
                    // Cập nhật mật khẩu mới trong cơ sở dữ liệu
                    if (passwordUpdate) Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()

                    binding.changeDataUser.text = "Cập nhật thông tin"
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
        }
    }

    private fun showOrHidePass() {
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

    private fun checkStateEdit(state: Boolean) {
        binding.nameDetail.isEnabled = state
        binding.phoneDetail.isEnabled = state
        binding.emailDetail.isEnabled = false
        binding.passDetail.isEnabled = state
        binding.ShowPassBtn.visibility = if (state) View.VISIBLE else View.GONE
    }

}