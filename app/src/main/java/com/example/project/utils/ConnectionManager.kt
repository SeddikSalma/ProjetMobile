package com.example.project.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ConnectionManager {
    private val _isConnected: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    fun updateConnectionState(state: Boolean){
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                _isConnected.value = state
            }
        }
    }
}