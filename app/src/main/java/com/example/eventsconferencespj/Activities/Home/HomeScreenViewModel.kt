package com.example.eventsconferencespj.Activities.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventsconferencespj.Fragments.Location.Location_Fragment
import com.example.eventsconferencespj.R

class HomeScreenViewModel : ViewModel() {
    private val _navigateToLocation = MutableLiveData<Unit>()
    val navigateToLocation: LiveData<Unit>
        get() = _navigateToLocation

    fun onNavLocationClicked() {
        // Xử lý khi nhấn vào nav_location
        _navigateToLocation.value = Unit
    }
}