package com.example.eventsconferencespj.Activities.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eventsconferencespj.Fragments.Location.Location_Fragment
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityHomeScreenBinding

class Home_Screen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // khởi tạo ViewModel
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        // Đổi màu hint của search bar
        var searchItem: SearchView? = binding.searchView
        if (searchItem != null) searchItem.setQueryHint(Html.fromHtml("<font color = #ffffff>" + "Tìm kiếm" + "</font>"))

        // Xử lý sự kiện khi nhấn vào nav_location
        val navLocation = binding.navLocation
        navLocation.setOnClickListener {
            viewModel.onNavLocationClicked()
        }
        viewModel.openLocation.observe(this, Observer {
            // Tạo Fragment cho fragment_location
            val fragmentLocation = Location_Fragment()
            // Mở Fragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_location, fragmentLocation)
            transaction.addToBackStack(null) // Để quay lại Fragment trước đó
            transaction.commit()
        })
    }
}