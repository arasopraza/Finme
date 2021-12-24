package com.arsy.finme.ui.register

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel(): ViewModel() {
    private var auth: FirebaseAuth? = null;

    init {
        auth = Firebase.auth
    }

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> = _registerStatus

    fun register(email: String, password: String){
        auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    _registerStatus.value = true
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    _registerStatus.value = false
                }
            }
    }
}