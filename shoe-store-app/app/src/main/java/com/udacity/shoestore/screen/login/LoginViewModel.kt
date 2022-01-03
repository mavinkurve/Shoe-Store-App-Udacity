package com.udacity.shoestore.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean>
        get() = _loginState

    private val _eventLoginCompleted = MutableLiveData<Boolean>()
    val eventLoginCompleted: LiveData<Boolean>
        get() = _eventLoginCompleted

    init {
        Log.i("LoginViewModel","LoginViewModel created!")
        _loginState.value = false
    }

    fun onSigninClick() {
        _loginState.value = true
    }

    fun onSignupClick() {
        _loginState.value = true
    }

    fun onLoginComplete() {
        _eventLoginCompleted.value = true
    }


}