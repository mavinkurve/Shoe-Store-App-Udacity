package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean>
        get() = _loginState

    private val _eventLoginCompleted = MutableLiveData<Boolean>()
    val eventLoginCompleted: LiveData<Boolean>
        get() = _eventLoginCompleted

    init {
        Timber.i("LoginViewModel created!")
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