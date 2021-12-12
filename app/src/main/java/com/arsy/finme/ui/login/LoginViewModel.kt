package com.arsy.finme.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsy.finme.data.source.FinmeRepository
import com.arsy.finme.data.source.local.entity.UserEntity
import kotlinx.coroutines.*

class LoginViewModel(private val repository: FinmeRepository) : ViewModel() {
    val inputUsername = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()

    private val _navigateToHome = MutableLiveData<Boolean>()

    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorPasswordToast = MutableLiveData<Boolean>()

    val errorPasswordToast: LiveData<Boolean>
        get() = _errorPasswordToast

    fun authUser() {
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val user = repository.loginUser(inputUsername.value!!)
                if (user.password == inputPassword.value) {
                    withContext(Dispatchers.Main) {
                        _navigateToHome.postValue(true)
                    }
                } else {
                    _errorPasswordToast.value = true
                }
            }
        }
    }

    fun doneToast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
}