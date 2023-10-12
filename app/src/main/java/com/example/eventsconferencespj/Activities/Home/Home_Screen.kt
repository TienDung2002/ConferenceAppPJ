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
import com.example.eventsconferencespj.Activities.Log.Login
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityHomeScreenBinding
import com.google.android.material.navigation.NavigationView

class Home_Screen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewModel: HomeScreenViewModel
    lateinit var drawerLayout : DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // khởi tạo ViewModel
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        // Đổi màu hint của search bar
        var searchItem: SearchView? = binding.searchView
        if (searchItem != null) searchItem.setQueryHint(Html.fromHtml("<font color = #ffffff>" + "Tìm kiếm" + "</font>"))


        // menu + toolbar
        val toolbar = binding.toolbar
        val navigationView = binding.navView
        drawerLayout = binding.drawerlayout
        setSupportActionBar(toolbar)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)

        binding.menuBtn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }


        // Xử lý sự kiện khi nhấn vào nav_location
//        binding.navLocation.setOnClickListener {
////            viewModel.onNavLocationClicked()
//            val intent = Intent(this, Location::class.java)
//            startActivity(intent)
//        }
//        viewModel.openLocation.observe(this, Observer {
//            // Tạo Fragment cho fragment_location
//            val fragmentLocation = Location_Fragment()
//            // Mở Fragment
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container_location, fragmentLocation)
//            transaction.addToBackStack(null) // Để quay lại Fragment trước đó
//            transaction.commit()
//        })
    }

    // Điều hướng menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//                R.nav_home -> supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_signout -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show()
            }
        }
        // Đóng thanh drawer
        binding.drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }
}