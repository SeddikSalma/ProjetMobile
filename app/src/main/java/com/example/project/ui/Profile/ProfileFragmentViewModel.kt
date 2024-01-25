package com.example.project.ui.Profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.RequestStatus
import com.example.project.dataclasses.Post
import com.example.project.dataclasses.User
import com.example.project.repository.UserRepository
import com.example.project.ui.Posts.CreatePostState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class CurrentUserState {
    data object Idle: CurrentUserState()
    data object Loading: CurrentUserState()
    data class Error(val error: String): CurrentUserState()
    data class Success(val user: User): CurrentUserState()
}

class ProfileFragmentViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _currentUser: MutableLiveData<CurrentUserState> = MutableLiveData<CurrentUserState>().apply {
        value = CurrentUserState.Idle
    }

    val currentUser: LiveData<CurrentUserState> = _currentUser

    fun getCurrentUser(){
        viewModelScope.launch {
            userRepository.getCurrentUser().collect {
                when(it) {
                    is RequestStatus.Waiting -> {
                        _currentUser.value = CurrentUserState.Loading
                    }
                    is RequestStatus.Success -> {
                        _currentUser.value = CurrentUserState.Success(it.data.data!!.user)
                    }
                    is RequestStatus.Error -> {
                        _currentUser.value = CurrentUserState.Error(it.message["message"]!!)
                    }
                }
            }
        }
    }
}