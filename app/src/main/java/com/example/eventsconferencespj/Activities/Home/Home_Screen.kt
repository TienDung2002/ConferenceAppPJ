package com.example.eventsconferencespj.Activities.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eventsconferencespj.Activities.Location
import com.example.eventsconferencespj.Activities.Log.Login
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityHomeScreenBinding
import com.google.android.material.navigation.NavigationView

//class Home_Screen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
class Home_Screen : AppCompatActivity(){
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // khởi tạo ViewModel
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        // Đổi màu hint của search bar
        var searchItem: SearchView? = findViewById(R.id.search_view)
        if (searchItem != null) searchItem.setQueryHint(Html.fromHtml("<font color = #ffffff>" + "Tìm kiếm" + "</font>"))


        // Xử lý sự kiện khi nhấn vào nav_location
        binding.navLocation.setOnClickListener {
            val intent = Intent(this, Location::class.java)
            startActivity(intent)
        }
    }
}
