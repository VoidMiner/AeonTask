package com.example.aeontask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aeontask.databinding.FragmentPaymentsBinding

class PaymentsFragment : Fragment(R.layout.fragment_payments) {
    private lateinit var binding: FragmentPaymentsBinding
    private val viewModel: PaymentsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentsBinding.bind(view)

        viewModel.getPayments()

        viewModel.paymentsResult.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                // Обновление интерфейса
            } else {
                // Обработка ошибки при получении списка
                binding.tvErrorPayments.visibility = View.VISIBLE
                binding.tvErrorPayments.text = result.exceptionOrNull()?.message ?: "Unknown error"
            }
        }
    }
}


