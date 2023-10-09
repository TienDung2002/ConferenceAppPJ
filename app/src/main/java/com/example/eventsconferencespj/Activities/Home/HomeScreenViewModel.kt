package com.example.eventsconferencespj.Activities.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventsconferencespj.Fragments.Location.Location_Fragment
import com.example.eventsconferencespj.R

class HomeScreenViewModel : ViewModel() {
    private val navigateToLocation = MutableLiveData<Unit>()
    val openLocation: LiveData<Unit>
        get() = navigateToLocation

    fun onNavLocationClicked() {
        // Xử lý khi nhấn vào nav_location
        navigateToLocation.value = Unit
    }
}