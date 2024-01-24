package com.example.project.ui.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.repository.UserRepository

class ProfileFragmentViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Profile Fragment"
    }
    val text: LiveData<String> = _text
}