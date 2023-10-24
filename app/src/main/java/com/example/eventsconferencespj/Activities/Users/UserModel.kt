package com.example.eventsconferencespj.Activities.Users

import android.text.Editable
import androidx.lifecycle.ViewModel

class UserModel : ViewModel() {
    var email : Editable? = null
    var pass : Editable? = null
    var name : Editable? = null
    var phone: Editable? = null
}