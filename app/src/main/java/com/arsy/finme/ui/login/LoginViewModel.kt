package com.arsy.finme.ui.login

import androidx.lifecycle.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel(), LifecycleObserver {
    private var auth: FirebaseAuth? = null
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        auth = Firebase.auth
        loading.postValue(false)
    }

    private val _signInStatus = MutableLiveData<Boolean>()
    val signInStatus: LiveData<Boolean> = _signInStatus

    fun signIn(email: String, password: String) {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO){
            try {
                auth?.let { login->
                    login.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task: Task<AuthResult> ->
                            if (task.isSuccessful) {
                                _signInStatus.postValue(true)
                                // Sign in success, update UI with the signed-in user's information
                            } else {
                                _signInStatus.postValue(false)
                            }
                            loading.postValue(false)
                        }
                }
            } catch (e: Exception){
                loading.postValue(false)
            }
        }
    }
}