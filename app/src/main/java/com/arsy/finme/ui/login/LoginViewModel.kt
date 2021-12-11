package com.arsy.finme.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsy.finme.data.source.FinmeRepository
import com.arsy.finme.data.source.local.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: FinmeRepository) : ViewModel() {
    val inputUsername = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val _navigateToHome = MutableLiveData<Boolean>()

    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    fun authUser() {
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val user = repository.loginUser(inputUsername.value!!)
                if (user != null) {
                    if (user.password == inputPassword.value) {
                        _navigateToHome.postValue(true)
                    } else {
                        Log.i("Finme", "Done Login")
                    }
                    Log.i("Finme", "Done Login")
                } else {
                    Log.i("Finme", "Failed Login")
                }
            }
        }
    }

    fun doneToast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
}