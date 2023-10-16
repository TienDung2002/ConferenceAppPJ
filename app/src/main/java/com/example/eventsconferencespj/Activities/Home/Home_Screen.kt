package com.example.eventsconferencespj.Activities.Home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsconferencespj.Activities.Location.Location
import com.example.eventsconferencespj.Activities.Users.User_Detail
import com.example.eventsconferencespj.R
import com.example.eventsconferencespj.databinding.ActivityHomeScreenBinding

//class Home_Screen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
class Home_Screen : AppCompatActivity(){
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewModel: HomeScreenViewModel
    private val List = mutableListOf<ConfeData>()
    companion object {
        private const val REQUEST_LOCATION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // khởi tạo ViewModel
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        // Đổi màu hint của search bar
        val searchItem: SearchView? =binding.searchView
        if (searchItem != null) searchItem.setQueryHint(Html.fromHtml("<font color = #ffffff>" + "Tìm kiếm" + "</font>"))
        searchItem!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Xử lý tìm kiếm ở đây

                // Ẩn bàn phím
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchItem.windowToken, 0)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Xử lý sự thay đổi văn bản trong ô tìm kiếm (nếu cần)

                return true
            }
        })

        // Nhấn vào nav_location
        binding.navLocation.setOnClickListener {
            val intent = Intent(this, Location::class.java)
            startActivity(intent)
        }

        // Nhấn vào nav_profile
        binding.navProfile.setOnClickListener{
            val intent = Intent(this, User_Detail::class.java)
            startActivity(intent)
        }

        viewModel.addConferenceData(ConfeData(1, "Lâu đài", "Hà nội", 100, 100000000, 15, 5, R.drawable.event_room_demo))
        viewModel.addConferenceData(ConfeData(2, "Lâu đài 2", "Hà nội 2", 1002, 10000002, 152, 5, R.drawable.event_room_demo))
        viewModel.addConferenceData(ConfeData(3, "Lâu đài 3", "Hà nội 3", 1003, 10000003, 1523, 5, R.drawable.event_room_demo))
        viewModel.addConferenceData(ConfeData(4, "Lâu đài", "Hà nội", 100, 100000000, 15, 5, R.drawable.event_room_demo))
        viewModel.addConferenceData(ConfeData(5, "Lâu đài 2", "Hà nội 2", 1002, 10000002, 152, 5, R.drawable.event_room_demo))
        viewModel.addConferenceData(ConfeData(6, "Lâu đài 3", "Hà nội 3", 1003, 10000003, 1523, 5, R.drawable.event_room_demo))
        // Gán danh sách dữ liệu từ ViewModel cho adapter
        val confDataList = viewModel.getConferenceDataList()
        binding.HorizontalRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.HorizontalRecyclerView.adapter = HomeAdapter(confDataList)
    }
}
