package com.example.aeontask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PaymentsViewModel(private val apiService: ApiService) : ViewModel() {
    private val _paymentsResult = MutableLiveData<Result<List<Payment>>>()
    val paymentsResult: LiveData<Result<List<Payment>>> get() = _paymentsResult



    fun getPayments() {
        viewModelScope.launch {
            try {
                val token = SessionManager.token ?: ""
                val response = apiService.getPayments(token)
                if (response.isSuccessful) {
                    val paymentsList = response.body()

                    // Проверка, что response.body() не null
                    if (paymentsList != null) {
                        _paymentsResult.value = Result.success(paymentsList)
                    } else {
                        _paymentsResult.value = Result.failure(Throwable("Список платежей равен null"))
                    }
                } else {
                    _paymentsResult.value = Result.failure(Throwable("Провал 404"))
                }
            } catch (e: Exception) {
                _paymentsResult.value = Result.failure(e)
            }
        }
    }
}
