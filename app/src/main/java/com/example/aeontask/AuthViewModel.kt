package com.example.aeontask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel(private val apiService: ApiService) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<TokenResponse>>()
    val loginResult: LiveData<Result<TokenResponse>> get() = _loginResult

    fun login(login: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(login, password))
                if (response.isSuccessful) {
                    val tokenResponse = response.body()

                    if (tokenResponse != null) {
                        SessionManager.token = tokenResponse.token
                        _loginResult.value = Result.success(tokenResponse)
                    } else {
                        _loginResult.value = Result.failure(Throwable("TokenResponse is null"))
                    }
                } else {
                    Log.e("AuthViewModel", "Неверный")
                    _loginResult.value = Result.failure(Throwable("Неверный логин"))
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Ошибка логина",e)
                _loginResult.value = Result.failure(e)
            }
        }
    }
}
