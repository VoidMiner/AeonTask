package com.example.aeontask

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aeontask.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels { AuthViewModelFactory(ApiClient.apiService) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(login, password)
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                // Переход к фрагменту PaymentsFragment
                findNavController().navigate(R.id.action_authFragment_to_paymentsFragment)
            } else {
                // Ошибка авторизации
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = result.exceptionOrNull()?.message ?: "Unknown error"
            }
        }

        // логаут
        binding.btnLogout.setOnClickListener {
            SessionManager.token = null

            findNavController().navigate(R.id.action_paymentsFragment_to_authFragment)
        }
    }
}


